package remote.jop.Control.User.MainUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import Model.User;
import remote.jop.Control.JobAdapter;
import remote.jop.Control.User.Login.UserLogin;
import remote.jop.JobActivity;
import remote.jop.R;

public class FavouriteFragment extends Fragment implements AdapterView.OnItemClickListener {

    private User user;
    private ListView listView;
    private JobAdapter jobAdapter;
    private LinearLayout mainLayout;
    private LinearLayout emptyLayout;
    public FavouriteFragment() {
        this.user = UserLogin.appUser;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        listView = view.findViewById(R.id.fav_jobs_user_fav);
        mainLayout = view.findViewById(R.id.content_layout);
        emptyLayout = view.findViewById(R.id.empty_layout);
        jobAdapter = new JobAdapter(user.getFavJobs(), getActivity().getApplicationContext());
        listView.setAdapter(jobAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(getActivity().getApplicationContext(), JobActivity.class).putExtra("job",
                user.getFavJobs().get(i)));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (user.getFavJobs().isEmpty()) {
            emptyLayout.setVisibility(View.VISIBLE);
            mainLayout.setVisibility(View.INVISIBLE);
        } else {
            emptyLayout.setVisibility(View.INVISIBLE);
            mainLayout.setVisibility(View.VISIBLE);
        }
        jobAdapter.notifyDataSetChanged();
    }
}
