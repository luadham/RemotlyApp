package remote.jop.Control.ResetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import remote.jop.R;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {

    private TextView exitFormButton;
    private EditText emailEditText;
    private Button resetButton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        firebaseAuth = FirebaseAuth.getInstance();

        exitFormButton = findViewById(R.id.exit_forget_pwd_form);
        exitFormButton.setOnClickListener(this);

        emailEditText = findViewById(R.id.email_resetPassword);
        setEmailValue();

        resetButton = findViewById(R.id.reset_pwd_reset_activity);
        resetButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset_pwd_reset_activity:
                resetPwd();
                return;
            case R.id.exit_forget_pwd_form:
                goBack(view);
                return;
        }
    }

    private void goBack(View view) {
        finish();
    }

    private void setEmailValue() {
        String userEmail = getIntent().getStringExtra("user_email");
        emailEditText.setText(userEmail);
    }

    private void resetPwd() {
        String email = emailEditText.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter Valid E-mail");
            emailEditText.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailEditText.setError("Please Enter E-mail");
            emailEditText.requestFocus();
            return;
        }

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {
                       goToCheckMailActivity();
                   } else {
                       Toast.makeText(ResetPassword.this, "There is Something Wrong", Toast.LENGTH_SHORT).show();
                   }
                });
    }
    private void goToCheckMailActivity() {
        startActivity(new Intent(this, ResetPasswordCheckEmail.class));
        finish();
    }
}