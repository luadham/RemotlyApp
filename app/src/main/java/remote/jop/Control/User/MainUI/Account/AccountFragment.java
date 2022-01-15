package remote.jop.Control.User.MainUI.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import Model.User;
import remote.jop.Control.ConnectionManager;
import remote.jop.Control.User.Login.UserLogin;
import remote.jop.R;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private User user = UserLogin.appUser;
    private ImageView logoutBtn;
    private ImageView settingBtn;
    private TextView userName;
    private FirebaseAuth firebaseAuth;
    private ConnectionManager manager = ConnectionManager.shared();

    public AccountFragment(User user) {
        
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        firebaseAuth = manager.getFirebaseAuth();

        userName = view.findViewById(R.id.user_name_account_fragment);
        userName.setText(user.getName());

        settingBtn = view.findViewById(R.id.account_setting_account_fragment);
        settingBtn.setOnClickListener(this);

        logoutBtn = view.findViewById(R.id.logout_account_fragment);
        logoutBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_account_fragment:
                doLogout();
                break;
            case R.id.account_setting_account_fragment:
                goToSettingFragment();
                break;
        }
    }


    private void goToSettingFragment() {

        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_activity_main, new AccountSettingFragment(user))
                .commit();
    }

    private void doLogout() {
        firebaseAuth.signOut();
        getActivity().finish();
        startActivity(new Intent(getActivity().getApplicationContext(), UserLogin.class));
    }

}
