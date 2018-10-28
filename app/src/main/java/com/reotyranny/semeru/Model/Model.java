package com.reotyranny.semeru.Model;

import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Model {

    /** Firebase query paths **/
    public static final String DONATIONS = "donations";
    public static final String LOCATIONS = "locations";
    public static final String USERS = "users";
  
    public interface FireBaseCallback {
        // when asynchronous query completes, run the given onCallback() method
        void onCallback(String locationName);
    }
    private static final Model _instance = new Model();

    public String userLocation = "";

    public static Model getInstance() {
        return _instance;
    }

    public FirebaseAuth getAuth() {
        return FirebaseAuth.getInstance();
    }

    public DatabaseReference getRef() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true); // important! -- caches up to 10MB of data
        return ref;
    }

    public FirebaseUser getUser() {
        return getAuth().getCurrentUser();
    }

    public void storeUser (String uid, final FireBaseCallback fireBaseCallback) {
        Query query = getRef().child(this.USERS).child(uid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("location")) {
                        fireBaseCallback.onCallback(dataSnapshot.child("location").getValue().toString());
                    } else {
                        fireBaseCallback.onCallback("");
                    }

                }
            }
        });
    }

}
