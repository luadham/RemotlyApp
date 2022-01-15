package remote.jop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JobDescriptionFragment extends Fragment {

    private TextView desc;
    private String descText;

    public JobDescriptionFragment() {}

    public JobDescriptionFragment(String descText) {
        this.descText = descText;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_description, container, false);
        desc = view.findViewById(R.id.job_desc_holder);
        desc.setText(this.descText);
        return view;
    }


}