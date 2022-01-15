package remote.jop.Control.User.MainUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

    public FavouriteFragment() {
        this.user = UserLogin.appUser;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        listView = view.findViewById(R.id.fav_jobs_user_fav);
        listView.setAdapter(new JobAdapter(user.getFavJobs(), getActivity().getApplicationContext()));
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(getActivity().getApplicationContext(), JobActivity.class).putExtra("job",
                user.getFavJobs().get(i)));
    }
}
