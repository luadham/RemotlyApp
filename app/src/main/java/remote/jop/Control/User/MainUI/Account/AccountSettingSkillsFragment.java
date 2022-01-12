package remote.jop.Control.MainUI.Account;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import remote.jop.R;

public class AccountSettingSkillsFragment extends Fragment {
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_setting_skills, container, false);
        floatingActionButton = view.findViewById(R.id.second_ui_account_setting_button);
        floatingActionButton.setOnClickListener(this::next);
        return view;
    }
    private void next(View view) {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_activity_main, new AccountSettingEmailFragment())
                .commit();
    }
}
