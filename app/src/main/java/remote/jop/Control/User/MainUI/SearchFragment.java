package remote.jop.Control.User.MainUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Model.Company;
import Model.Job;
import remote.jop.Control.ConnectionManager;
import remote.jop.Control.JobAdapter;
import remote.jop.R;

public class SearchFragment extends Fragment {

    private ConnectionManager manager = ConnectionManager.shared();
    private DatabaseReference databaseReference;
    private final String jobsCollections = "Jobs";
    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayList<Job> jobs;
    private DataSnapshot snapshot;
    private Job job;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.jobs_list_view);

        arrayList = new ArrayList<>();
        jobs = new ArrayList<>();

        JobAdapter jobAdapter = new JobAdapter(jobs, getActivity().getApplicationContext());

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

        databaseReference = manager.getDatabaseReference().getReference(jobsCollections);

        Query query = databaseReference.orderByChild("jobTitle");

        listView.setAdapter(jobAdapter);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    job = new Job();
                    job.setCompanyName((String) dataSnapshot.child("company").child("name").getValue());
                    job.setCompanyEmail((String) dataSnapshot.child("company").child("email").getValue());
                    job.setJobDesc((String) dataSnapshot.child("jobDesc").getValue());
                    job.setJobLocation((String) dataSnapshot.child("jobLocation").getValue());
                    job.setJobRequirements((String) dataSnapshot.child("jobRequirements").getValue());
                    job.setJobTitle((String) dataSnapshot.child("jobTitle").getValue());
                    job.setSalary(Double.parseDouble(dataSnapshot.child("salary").getValue().toString()));
                    jobs.add(job);
                }
                jobAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
