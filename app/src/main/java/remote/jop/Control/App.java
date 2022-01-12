package remote.jop.Control;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // test
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
