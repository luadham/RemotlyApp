package remote.jop.Control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Job;
import remote.jop.R;

public class JobAdapter extends BaseAdapter {

    private ArrayList<Job> jobs;
    private Context context;


    public JobAdapter(ArrayList<Job> jobs, Context context) {
        this.jobs = jobs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return jobs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.job_adapter_view, viewGroup, false);
        TextView jobTitle = view.findViewById(R.id.job_title_job_adapter);
        TextView jobDesc = view.findViewById(R.id.job_dec_job_adapter);
        jobTitle.setText(jobs.get(i).getJobTitle());
        jobDesc.setText(jobs.get(i).getJobDesc());
        return view;
    }
}
