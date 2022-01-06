package remote.jop.Control.SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import Model.User;
import remote.jop.Control.ConnectionManager;
import remote.jop.R;

public class UserSignUp extends AppCompatActivity implements View.OnClickListener {

    private TextView exitActivity, loginAgain;
    private EditText userPwd;
    private EditText userEmail;
    private EditText userName;
    private Button signUp;

    private final int MAX_PWD_LEN = 8;

    private FirebaseAuth firebaseAuth;
    private ConnectionManager manager = ConnectionManager.shared();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        firebaseAuth = manager.getFirebaseAuth();

        exitActivity = findViewById(R.id.exit_sign_up_form);
        exitActivity.setOnClickListener(this);

        loginAgain = findViewById(R.id.login_sign_up);
        loginAgain.setOnClickListener(this);

        userPwd = findViewById(R.id.password_sign_up);

        signUp = findViewById(R.id.sign_up_button);
        signUp.setOnClickListener(this);

        userName = findViewById(R.id.user_name_sign_up);
        userEmail = findViewById(R.id.email_sign_up);
        userPwd = findViewById(R.id.password_sign_up);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exit_sign_up_form:
            case R.id.login_sign_up:
                goBack(view);
                break;
            case R.id.sign_up_button:
                registerAccount();
        }
    }

    private void registerAccount() {
        String name = userName.getText().toString().trim();
        String email = userEmail.getText().toString().trim();
        String pwd = userPwd.getText().toString();

        if (name.isEmpty()) {
            userName.setError("Enter Valid Name");
            userName.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()) {
            userEmail.setError("Enter Valid Email");
            userEmail.requestFocus();
            return;
        }

        if (pwd.isEmpty()) {
            userPwd.setError("Enter Valid Password");
            userPwd.requestFocus();
            return;
        }

        if (pwd.length() < MAX_PWD_LEN) {
            userPwd.setError("Please Enter Strong Password");
            userPwd.requestFocus();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        User user = new User(name, email, pwd);
                        manager.getDatabaseReference()
                                .getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user)
                                .addOnCompleteListener(signUpTask -> {
                                    if (signUpTask.isSuccessful()) {
                                        Toast.makeText(UserSignUp.this, "Sign Up Done", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(UserSignUp.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(UserSignUp.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    }
                });


    }


    private void goBack(View view) {
        finish();
    }


}