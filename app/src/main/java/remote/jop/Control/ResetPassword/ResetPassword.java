package remote.jop.Control.ResetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import remote.jop.R;

public class ResetPassword extends AppCompatActivity {
    private TextView exitFormButton;
    private EditText email;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        exitFormButton = findViewById(R.id.exit_forget_pwd_form);
        email = findViewById(R.id.email_resetPassword);
        resetButton = findViewById(R.id.reset_pwd_reset_activity);

        setEmailValue();
        exitFormButton.setOnClickListener(this::goBack);
        resetButton.setOnClickListener(this::goToCheckMailActivity);
    }

    void goBack(View view) {
        finish();
    }

    void setEmailValue() {
        String userEmail = getIntent().getStringExtra("user_email");
        email.setText(userEmail);
    }

    void goToCheckMailActivity(View view) {
        startActivity(new Intent(this, ResetPasswordCheckEmail.class));
        finish();
    }
}