package remote.jop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import Model.Job;

public class JobActivity extends AppCompatActivity {

    private Job job;
    private TextView jobName;
    private TextView jobCompany;
    private TextView jobSalary;
    private TextView jobLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        jobName = findViewById(R.id.job_name);
        jobCompany = findViewById(R.id.job_company);
        jobSalary = findViewById(R.id.job_salary);
        jobLocation = findViewById(R.id.job_location);
        job = (Job) getIntent().getSerializableExtra("job");
        setData();
    }

    private void setData() {
        jobName.setText(job.getJobTitle());
        jobCompany.setText(job.getCompanyName());
        jobSalary.setText(String.valueOf(job.getSalary()));
        jobLocation.setText(job.getJobLocation());
    }
}