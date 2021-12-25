package remote.jop.Control.MainUI.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Model.User;
import remote.jop.R;

public class AccountSettingFragment extends Fragment {

    private User user;
    private EditText userName;
    private EditText userJob;
    private EditText userDesc;
    private FloatingActionButton floatingActionButton;

    public AccountSettingFragment(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_setting_fragment, container, false);

        userName = view.findViewById(R.id.user_name_account_setting);
        userJob = view.findViewById(R.id.user_job_account_setting);
        userDesc = view.findViewById(R.id.user_desc_account_setting);
        floatingActionButton = view.findViewById(R.id.first_ui_account_setting_button);

        userName.setText(user.getName());
        userJob.setText(user.getJob());
        userDesc.setText(user.getDesc());

        floatingActionButton.setOnClickListener(this::next);
        return view;
    }

    private void next(View view) {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_activity_main, new AccountSettingSkillsFragment())
                .commit();
    }

}
