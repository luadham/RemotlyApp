package remote.jop.Control.User.MainUI;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import Model.User;
import remote.jop.Control.User.MainUI.Account.AccountFragment;
import remote.jop.R;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView navigationBar;
    private User user;
    private Fragment selectedFragment;
    private Fragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new SearchFragment();
        selectedFragment = mainFragment;
        initMainFragment();

        navigationBar = findViewById(R.id.bottom_navigation_activity_main);

        navigationBar.setOnItemSelectedListener(this::switchNavigationFragment);
    }

    @SuppressLint("NonConstantResourceId")
    private boolean switchNavigationFragment(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_navigation_item:
                selectedFragment = mainFragment;
                break;
            case R.id.fav_navigation_item:
                selectedFragment = new FavouriteFragment();
                break;
            case R.id.account_navigation_item:
                selectedFragment = new AccountFragment(user);
                break;
        }

        assert selectedFragment != null;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_activity_main, selectedFragment)
                .commit();

        return true;
    }

    private void initMainFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_activity_main, mainFragment)
                .commit();
    }

}