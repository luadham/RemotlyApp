package remote.jop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Job;
import Model.User;
import remote.jop.Control.ConnectionManager;
import remote.jop.Control.User.Login.UserLogin;

public class JobActivity extends AppCompatActivity implements View.OnClickListener {


    private Job job;
    private TextView jobName;
    private TextView jobCompany;
    private TextView jobSalary;
    private TextView jobLocation;
    private LottieAnimationView favButton;
    private User user;
    private ConnectionManager manager = ConnectionManager.shared();
    private FirebaseDatabase databaseReference;
    private DataSnapshot userSnapShot;
    private ArrayList<Job> jobs;
    private TextView jobDesc;
    private TextView jobReq;
    private Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        jobName = findViewById(R.id.job_name);
        jobCompany = findViewById(R.id.job_company);
        jobSalary = findViewById(R.id.job_salary);
        jobLocation = findViewById(R.id.job_location);
        favButton = findViewById(R.id.fav_button_job_activity);
        jobDesc = findViewById(R.id.job_desc_button);
        jobReq = findViewById(R.id.job_req_button);
        apply = findViewById(R.id.apply_to_job_button);
        
        apply.setOnClickListener(this);
        jobReq.setOnClickListener(this);
        jobDesc.setOnClickListener(this);

        databaseReference = manager.getDatabaseReference();
        favButton.setOnClickListener(this);
        job = (Job) getIntent().getSerializableExtra("job");

        if (checkFavButton()) {
            favButton.setAnimation(R.raw.jfav);
            favButton.playAnimation();
        } else {
            favButton.setAnimation(R.raw.unfav);
            favButton.playAnimation();
        }
        showJobDesc();
        setData();
    }

    private boolean checkFavButton() {
        for (Job j : UserLogin.appUser.getFavJobs()) {
            if (job.getJobTitle().equals(j.getJobTitle())) {
                return true;
            }
        }
        return false;
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
            case R.id.job_desc_button:
                showJobDesc();
                break;
            case R.id.job_req_button:
                showJobReq();
                break;
            case R.id.apply_to_job_button:
                sendMessageToCompany();
                break;
        }
    }

    private void sendMessageToCompany() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{job.getCompanyEmail()});
        email.putExtra(Intent.EXTRA_SUBJECT, "Applying For Job");
        email.putExtra(Intent.EXTRA_TEXT, "I Want to apply for " + job.getJobTitle() + " My E-mail: " + UserLogin.appUser.getEmail());
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    private void showJobDesc() {
        jobReq.setTextColor(Color.GRAY);
        jobDesc.setTextColor(Color.BLACK);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.job_activity_frame, new JobDescriptionFragment(job.getJobDesc()))
                .commit();
    }

    private void showJobReq() {
        jobReq.setTextColor(Color.BLACK);
        jobDesc.setTextColor(Color.GRAY);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.job_activity_frame, new JobRequirmentFragment(job.getJobRequirements()))
                .commit();
    }

    private int getIdx(Job job) {
        for (int i = 0; i < UserLogin.appUser.getFavJobs().size(); i++) {
            if (UserLogin.appUser.getFavJobs().get(i).getJobTitle().equals(job.getJobTitle()) &&
                    UserLogin.appUser.getFavJobs().get(i).getCompanyName().equals(job.getCompanyName()))
                return i;
        }
        return -1;
    }
    private void addJobToFav() {
        if (checkFavButton()) {
            UserLogin.appUser.getFavJobs().remove(getIdx(job));
            favButton.setAnimation(R.raw.unfav);
            favButton.playAnimation();
            Toast.makeText(this, "Job Removed", Toast.LENGTH_SHORT).show();
        } else {
            UserLogin.appUser.getFavJobs().add(job);
            favButton.setAnimation(R.raw.jfav);
            favButton.playAnimation();
            Toast.makeText(this, "Job Added", Toast.LENGTH_SHORT).show();
        }
        updateDatabase();
    }


    private void updateDatabase() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("favJobs", UserLogin.appUser.getFavJobs());
        manager.getDatabaseReference().getReference("Users").child(UserLogin.appUser.getUid()).updateChildren(hashMap);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    
}