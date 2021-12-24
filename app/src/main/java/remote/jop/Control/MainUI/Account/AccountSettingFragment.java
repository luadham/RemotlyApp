package remote.jop.Control.MainUI.Account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import Model.User;
import remote.jop.R;

public class AccountSettingFragment extends Fragment {

    private User user;
    public AccountSettingFragment(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.account_setting_fragment, container, false);
    }

}
