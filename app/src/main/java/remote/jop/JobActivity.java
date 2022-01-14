package remote.jop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import Model.Job;
import Model.User;
import remote.jop.Control.ConnectionManager;

public class JobActivity extends AppCompatActivity implements View.OnClickListener {


    private Job job;
    private TextView jobName;
    private TextView jobCompany;
    private TextView jobSalary;
    private TextView jobLocation;
    private ImageButton favButton;
    private User user;
    private ConnectionManager manager = ConnectionManager.shared();
    private FirebaseDatabase databaseReference;
    private DataSnapshot userSnapShot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        jobName = findViewById(R.id.job_name);
        jobCompany = findViewById(R.id.job_company);
        jobSalary = findViewById(R.id.job_salary);
        jobLocation = findViewById(R.id.job_location);
        favButton = findViewById(R.id.fav_button_job_activity);

        databaseReference = manager.getDatabaseReference();

        favButton.setOnClickListener(this);

        job = (Job) getIntent().getSerializableExtra("job");
        user = (User) getIntent().getSerializableExtra("user");

        setData();
    }

    private void setData() {
        jobName.setText(job.getJobTitle());
        jobCompany.setText(job.getCompanyName());
        jobSalary.setText(String.valueOf(job.getSalary()));
        jobLocation.setText(job.getJobLocation());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fav_button_job_activity:
                addJobToFav();
                break;
        }
    }

    private void addJobToFav() {
        user.getFavouriteJobs().add(job);
        updateDatabase();
    }
    private void updateDatabase() {
        Query query = databaseReference.getReference("Users").orderByChild("email");
        DatabaseReference firebaseDatabase = manager.getDatabaseReference().getReference();
        System.out.println("Adham"+firebaseDatabase.getKey());

    }
}