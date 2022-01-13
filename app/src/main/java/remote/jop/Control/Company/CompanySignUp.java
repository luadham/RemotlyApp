package remote.jop.Control.Company;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import Model.Company;
import Model.User;
import remote.jop.Control.ConnectionManager;
import remote.jop.Control.User.UserSignUp.UserSignUp;
import remote.jop.R;

public class CompanySignUp extends AppCompatActivity implements View.OnClickListener {

    private TextView exit;
    private TextView login;
    private TextView companyName;
    private TextView companyEmail;
    private TextView companyPwd;
    private Button signUp;
    private ConnectionManager manager = ConnectionManager.shared();
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_sign_up);
        exit = findViewById(R.id.company_exit_sign_up_form);
        login = findViewById(R.id.company_login_sign_up);
        companyName = findViewById(R.id.company_name_sign_up_form);
        companyEmail = findViewById(R.id.company_email_sign_up_form);
        companyPwd = findViewById(R.id.company_password_sign_up_form);
        signUp = findViewById(R.id.company_sign_up_button);
        firebaseAuth = manager.getFirebaseAuth();
        signUp.setOnClickListener(this);
        login.setOnClickListener(this);
        exit.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.company_exit_sign_up_form:
            case R.id.company_login_sign_up:
                finish();
                startActivity(new Intent(this, CompanyLogin.class));
                return;
            case R.id.company_sign_up_button:
                registerAccount();
                return;
        }
    }

    private void registerAccount() {
        String name = companyName.getText().toString().trim();
        String email = companyEmail.getText().toString().trim();
        String pwd = companyPwd.getText().toString();

        if (name.isEmpty()) {
            companyName.setError("Enter Valid Name");
            companyName.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()) {
            companyEmail.setError("Enter Valid Email");
            companyEmail.requestFocus();
            return;
        }

        if (pwd.isEmpty()) {
            companyPwd.setError("Enter Valid Password");
            companyPwd.requestFocus();
            return;
        }

        if (pwd.length() < 8) {
            companyPwd.setError("Please Enter Strong Password");
            companyPwd.requestFocus();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Company company = new Company(name, email, pwd);
                        manager.getDatabaseReference()
                                .getReference("Companies")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(company)
                                .addOnCompleteListener(signUpTask -> {
                                    if (signUpTask.isSuccessful()) {
                                        Toast.makeText(CompanySignUp.this, "Sign Up Done", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(CompanySignUp.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(CompanySignUp.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}