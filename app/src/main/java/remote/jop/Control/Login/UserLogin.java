package remote.jop.Control.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;
import remote.jop.Control.MainUI.MainActivity;
import remote.jop.Control.ResetPassword.ResetPassword;
import remote.jop.Control.SignUp.UserSignUp;
import remote.jop.R;

public class UserLogin extends AppCompatActivity implements View.OnClickListener {

    private final String USERS_COLLECTION = "Users";
    private ImageView logoImageView;
    private TextView signUpButton;
    private TextView forgetPassword;
    private EditText emailEditText;
    private EditText pwdEditText;
    private Button loginButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private User appUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference(USERS_COLLECTION);

        signUpButton = findViewById(R.id.sign_up_login_form);
        signUpButton.setOnClickListener(this);

        forgetPassword = findViewById(R.id.forget_password_login_form);
        forgetPassword.setOnClickListener(this);

        logoImageView = findViewById(R.id.logo_login_form);
        emailEditText = findViewById(R.id.email_text_login_form);
        pwdEditText = findViewById(R.id.password_text_login_form);

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_password_login_form:
                gotToForgetPasswordForm(view);
                return;
            case R.id.sign_up_login_form:
                goToSignUpForm(view);
                return;
            case R.id.login_button:
                doLogin();
                return;
        }
    }

    void goToSignUpForm(View view) {
        startActivity(new Intent(this, UserSignUp.class));
    }

    void gotToForgetPasswordForm(View view) {
        Intent forgetPwdIntent = new Intent(this, ResetPassword.class);
        forgetPwdIntent.putExtra("user_email", emailEditText.getText().toString());
        startActivity(forgetPwdIntent);
    }
    void startAnimations() {
        logoImageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce));
    }
    void doLogin() {
        String email = emailEditText.getText().toString().trim();
        String pwd = pwdEditText.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("You Must Enter E-mail");
            emailEditText.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailEditText.setError("Email Is Required");
            emailEditText.requestFocus();
            return;
        }

        if (pwd.isEmpty()) {
            pwdEditText.setError("Password is Required");
            pwdEditText.requestFocus();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, pwd)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        user = authResult.getUser();
                        databaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                appUser = snapshot.getValue(User.class);
                                startActivity(new Intent(UserLogin.this, MainActivity.class).putExtra("user", appUser));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        startAnimations();
    }

}