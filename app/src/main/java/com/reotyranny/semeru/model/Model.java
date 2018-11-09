package com.reotyranny.semeru.model;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;

/**
 * Interfaces with model objects.
 */
public class Model {

    /**
     * Runs the given onCallback() method.
     */
    public interface FireBaseCallback {

        // when asynchronous query completes, run the given onCallback() method
        void onCallback(String locationName);
    }

    /**
     * Firebase query paths
     **/
    public static final String DONATIONS = "donations";

    public static final String LOCATIONS = "locations";

    public static final String USERS = "users";

    private static final Model _instance = new Model();

    private String userLocation;

    /**
     * Returns an instance of the model.
     *
     * @return an instance of the model.
     */
    public static Model getInstance() {
        return _instance;
    }

    public FirebaseAuth getAuth() {
        return FirebaseAuth.getInstance();
    }

    /**
     * Returns a Realtime DB reference.
     *
     * @return a Realtime DB reference
     */
    public DatabaseReference getRef() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true); // important! -- caches up to 10MB of data
        return ref;
    }

    /**
     * Returns a Firebase user.
     *
     * @return a Firebase user
     */
    public FirebaseUser getUser() {
        return getAuth().getCurrentUser();
    }

    /**
     * Returns the location associated with a Firebase user.
     *
     * @return the location associated with a Firebase user
     */
    public String getUserLocation() {
        return userLocation;
    }

    /**
     * Setter method for a user location in the model.
     *
     * @param userLocation a valid location
     */
    public void setUserLocation(final String userLocation) {
        this.userLocation = userLocation;
    }

    /**
     * Stores a user in Firebase.
     *
     * @param uid              a Firebase UID
     * @param fireBaseCallback a callback to be run after the async function is done.
     */
    public void storeUser(String uid, final FireBaseCallback fireBaseCallback) {
        Query query = getRef().child(USERS).child(uid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
            }

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("location")) {
                        fireBaseCallback.onCallback(
                                Objects.requireNonNull(dataSnapshot.child("location").getValue()).toString());
                    } else {
                        fireBaseCallback.onCallback("");
                    }

                }
            }
        });
    }

}
