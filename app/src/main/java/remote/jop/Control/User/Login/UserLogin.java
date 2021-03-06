package remote.jop.Control.User.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import Model.User;
import remote.jop.Control.ConnectionManager;
import remote.jop.Control.User.MainUI.MainActivity;
import remote.jop.Control.ResetPassword.ResetPassword;
import remote.jop.Control.User.UserSignUp.UserSignUp;
import remote.jop.R;

public class UserLogin extends AppCompatActivity implements View.OnClickListener {

    private final String USERS_COLLECTION = "Users";
    private String userId;
    private ImageView logoImageView;
    private TextView signUpButton;
    private TextView forgetPassword;
    private EditText emailEditText;
    private EditText pwdEditText;
    private Button loginButton;
    private DatabaseReference databaseReference;
    public static User appUser;
    private ConnectionManager manager = ConnectionManager.shared();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        databaseReference = manager.getDatabaseReference().getReference().child(USERS_COLLECTION);

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
        Query q = databaseReference.orderByChild("email");

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("email").getValue().toString().equals(email) && child.child("pwd").getValue().toString().equals(pwd)) {
                        userId = child.getKey();
                    }
                }
                if (userId == null) {
                    Toast.makeText(UserLogin.this, "Wrong E-mail or password", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        appUser = snapshot.getValue(User.class);
                        appUser.setUid(userId);
                        finish();
                        startActivity(new Intent(UserLogin.this, MainActivity.class).putExtra("user", appUser));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        startAnimations();
    }

}