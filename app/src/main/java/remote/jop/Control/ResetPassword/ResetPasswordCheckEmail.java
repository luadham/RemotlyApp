package remote.jop.Control.ResetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import remote.jop.R;

public class ResetPasswordCheckEmail extends AppCompatActivity implements View.OnClickListener {

    private TextView skipForget;
    private Button openEmailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_check_email);

        skipForget = findViewById(R.id.skip_forget_form);
        skipForget.setOnClickListener(this);

        openEmailBtn = findViewById(R.id.open_email_app_reset_form);
        openEmailBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.skip_forget_form:
                goToLoginActivity();
                break;
            case R.id.open_email_app_reset_form:
                openEmailApp();
                break;
        }
    }

    private void openEmailApp() {
        Intent openMailIntent = new Intent(Intent.ACTION_MAIN);
        openMailIntent.addCategory(Intent.CATEGORY_APP_EMAIL);
        startActivity(Intent.createChooser(openMailIntent, "Open Mail"));
    }

    void goToLoginActivity() {
        finish();
    }
}