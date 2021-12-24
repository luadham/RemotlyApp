package remote.jop.Control.MainUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import Model.User;
import remote.jop.Control.Login.UserLogin;
import remote.jop.R;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private User user;

    private ImageView logoutBtn;
    private TextView userName;
    FirebaseAuth firebaseAuth;

    public AccountFragment(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        userName = view.findViewById(R.id.user_name_account_fragment);
        userName.setText(user.getName());

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
        }
    }

    private void doLogout() {
        firebaseAuth.signOut();
        getActivity().finish();
        startActivity(new Intent(getActivity().getApplicationContext(), UserLogin.class));
    }

}
