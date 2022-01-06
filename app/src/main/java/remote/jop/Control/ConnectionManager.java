package remote.jop.Control;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConnectionManager {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase databaseReference;


    private static final ConnectionManager manager = new ConnectionManager();

    private ConnectionManager() {

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance();
    }

    public static ConnectionManager shared() {

        return manager;
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseDatabase getDatabaseReference() {
        if (databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance();
        }
        return databaseReference;
    }

}
