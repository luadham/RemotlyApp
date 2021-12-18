package remote.jop.Control.MainUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import remote.jop.R;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMainFragment();

        navigationBar = findViewById(R.id.bottom_navigation_activity_main);
        navigationBar.setOnItemSelectedListener(this::switchNavigationFragment);

    }

    private boolean switchNavigationFragment(MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.search_navigation_item:
                selectedFragment = new SearchFragment();
                break;
            case R.id.fav_navigation_item:
                selectedFragment = new FavouriteFragment();
                break;
            case R.id.account_navigation_item:
                selectedFragment = new AccountFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_activity_main, selectedFragment)
                .commit();

        return true;
    }

    private void initMainFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_activity_main, new SearchFragment())
                .commit();
    }
}