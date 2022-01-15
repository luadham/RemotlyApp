package remote.jop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class JobRequirmentFragment extends Fragment {

    private TextView req;
    private String reqTxt;

    public JobRequirmentFragment() {

    }

    public JobRequirmentFragment(String reqTxt) {
        this.reqTxt = reqTxt;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_requirment, container, false);
        req = view.findViewById(R.id.job_req_holder);
        req.setText(reqTxt);
        return view;
    }
}