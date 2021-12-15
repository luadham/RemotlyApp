package remote.jop.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import remote.jop.R;

public class UserLogin extends AppCompatActivity {
    private ImageView logoImageView;
    private TextView signUpButton, forgetPassword;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        logoImageView = findViewById(R.id.logo_login_form);
        signUpButton = findViewById(R.id.sign_up_login_form);
        forgetPassword = findViewById(R.id.forget_password_login_form);
        emailEditText = findViewById(R.id.email_text_login_form);

        signUpButton.setOnClickListener(this::goToSignUpForm);
        forgetPassword.setOnClickListener(this::gotToForgetPasswordForm);

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

    @Override
    protected void onResume() {
        super.onResume();
        startAnimations();
    }
}