package remote.jop.Control.MainUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;
import remote.jop.R;

public class MainActivity extends AppCompatActivity {

    private final String USERS_COLLECTION = "Users";
    private BottomNavigationView navigationBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private String userId;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference(USERS_COLLECTION);

        userId = firebaseUser.getUid();

        // System.out.println("Adham I'm Here : " + userId);

        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                selectedFragment = new AccountFragment(user);
                break;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_activity_main, selectedFragment)
                .commit();

        return true;
    }

    private void initMainFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_activity_main, new SearchFragment())
                .commit();
    }

}