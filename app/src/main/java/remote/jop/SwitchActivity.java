package remote.jop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import remote.jop.Control.Company.CompanyLogin;
import remote.jop.Control.User.Login.UserLogin;

public class SwitchActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton seeker;
    ImageButton company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        seeker = findViewById(R.id.user_login_switch_activity);
        company = findViewById(R.id.company_login_switch_activity);
        seeker.setOnClickListener(this);
        company.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_login_switch_activity:
                startActivity(new Intent(this, UserLogin.class));
                return;
            case R.id.company_login_switch_activity:
                startActivity(new Intent(this, CompanyLogin.class));
                return;
        }
    }
}