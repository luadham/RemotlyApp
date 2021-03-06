package remote.jop.Control.Company;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Company;
import Model.Job;
import remote.jop.Control.ConnectionManager;
import remote.jop.R;

public class CompanyPostJob extends AppCompatActivity implements View.OnClickListener {

    private EditText jopReq;
    private EditText jopLocation;
    private EditText jopDesc;
    private EditText jopTitle;
    private EditText jopSalary;
    private Button postButton;
    private Company company;
    private ConnectionManager manager = ConnectionManager.shared();
    private DatabaseReference firebaseDatabase;
    private final String jops = "Jobs";
    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_post_job);
        jopTitle = findViewById(R.id.job_title);
        jopReq = findViewById(R.id.job_requirements);
        jopLocation = findViewById(R.id.job_location);
        jopDesc = findViewById(R.id.job_description);
        jopSalary = findViewById(R.id.jop_salary);
        postButton = findViewById(R.id.posting_job_button);
        company = (Company) getIntent().getSerializableExtra("company");
        //Toast.makeText(this, company.getEmail(), Toast.LENGTH_SHORT).show();

        postButton.setOnClickListener(this);

    }
    private Job getJob() {
        String title = jopTitle.getText().toString();
        String jobReq = jopReq.getText().toString();
        String jobLoc = jopLocation.getText().toString();
        String jobDec = jopDesc.getText().toString();
        double sal = Double.parseDouble(jopSalary.getText().toString());
        return new Job(title, jobReq, jobLoc, jobDec, company, sal);
    }
    private void postJob() {
        job = getJob();
        firebaseDatabase = manager.getDatabaseReference().getReference(jops);
        firebaseDatabase.push().setValue(job);
        jopTitle.setText("");
        jopReq.setText("");
        jopLocation.setText("");
        jopDesc.setText("");
        jopSalary.setText("0");
        Toast.makeText(this, "Job Posted", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view) {
        postJob();
    }
}