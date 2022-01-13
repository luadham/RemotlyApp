package remote.jop.Control.Company;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import Model.Company;
import Model.User;
import remote.jop.Control.ConnectionManager;
import remote.jop.Control.ResetPassword.ResetPassword;
import remote.jop.Control.User.Login.UserLogin;
import remote.jop.Control.User.MainUI.MainActivity;
import remote.jop.R;

public class CompanyLogin extends AppCompatActivity implements View.OnClickListener {

    private TextView signUp;
    private TextView forgetPwd;
    private EditText companyEmail;
    private EditText companyPwd;
    private Button loginBtn;
    private ConnectionManager manager = ConnectionManager.shared();
    private final String companiesCollection = "Companies";
    private DatabaseReference databaseReference;
    private Company company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);
        signUp = findViewById(R.id.sign_up_company_login_form);
        companyEmail = findViewById(R.id.company_email_text_login_form);
        companyPwd = findViewById(R.id.company_password_text_login_form);
        loginBtn = findViewById(R.id.company_login_button);
        forgetPwd = findViewById(R.id.forget_password_company_login_form);
        databaseReference = manager.getDatabaseReference().getReference().child(companiesCollection);
        loginBtn.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_company_login_form:
                finish();
                startActivity(new Intent(this, CompanySignUp.class));
                return;
            case R.id.forget_password_company_login_form:
                startActivity(new Intent(this, ResetPassword.class));
                return;
            case R.id.company_login_button:
                doLogin();
                return;

        }
    }

    void doLogin() {
        String email = companyEmail.getText().toString().trim();
        String pwd = companyPwd.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            companyEmail.setError("You Must Enter E-mail");
            companyEmail.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            companyEmail.setError("Email Is Required");
            companyEmail.requestFocus();
            return;
        }

        if (pwd.isEmpty()) {
            companyPwd.setError("Password is Required");
            companyPwd.requestFocus();
            return;
        }
        Query q = databaseReference.orderByChild("email").equalTo(email);

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String companyId = null;
                for (DataSnapshot child : snapshot.getChildren()) {
                    companyId = child.getKey();
                }
                if (companyId == null) {
                    Toast.makeText(CompanyLogin.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                databaseReference.child(companyId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        company = snapshot.getValue(Company.class);
                        finish();
                        startActivity(new Intent(CompanyLogin.this, CompanyPostJob.class).putExtra("company", company));
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
}