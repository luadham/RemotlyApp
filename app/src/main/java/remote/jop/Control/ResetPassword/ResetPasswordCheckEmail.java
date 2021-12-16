package remote.jop.Control.ResetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import remote.jop.R;

public class ResetPasswordCheckEmail extends AppCompatActivity {
    private TextView skipForget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_check_email);
        skipForget = findViewById(R.id.skip_forget_form);
        skipForget.setOnClickListener(this::goToLoginActivity);
    }

    void goToLoginActivity(View view) {
        finish();
    }
}