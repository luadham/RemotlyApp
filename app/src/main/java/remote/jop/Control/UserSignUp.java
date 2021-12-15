package remote.jop.Control;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import remote.jop.R;

public class UserSignUp extends AppCompatActivity {
    private TextView exitActivity, loginAgain;
    private EditText userPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        exitActivity = findViewById(R.id.exit_sign_up_form);
        loginAgain = findViewById(R.id.login_sign_up);
        userPwd = findViewById(R.id.password_sign_up);

        exitActivity.setOnClickListener(this::goBack);
        loginAgain.setOnClickListener(this::goBack);

    }

    void goBack(View view) {
        finish();
    }


}