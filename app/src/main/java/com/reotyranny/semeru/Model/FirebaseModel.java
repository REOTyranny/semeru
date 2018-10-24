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

public class FirebaseModel {

    /** Singleton instance */
    private static final FirebaseModel _instance = new FirebaseModel();
    public static FirebaseModel getInstance() { return _instance; }

    public boolean correctLocation = false;
    public String userLocation = "";


    public DatabaseReference getDatabaseReference() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.keepSynced(true); // important! -- caches up to 10MB of data
        return ref;
    }

    public FirebaseAuth getAuthInstance() {
        return FirebaseAuth.getInstance();
    }

    public FirebaseUser getCurrentUser() {
        return getAuthInstance().getCurrentUser();
    }

    // make it public boolean (to check valid add)
    public void addAccount(String name, String email, AccountType acctType, String location){
        Account account = new Account(name, email, acctType, location);
        getDatabaseReference().child("users").push().setValue(account);
    }

    // TODO
    public void addLocation(){
    }

    public interface FireBaseCallback {
        void onCallback(boolean isValid);
    }

    public interface FireBaseCallback2 {
        void onCallback(String locationName);
    }


    // instead restrict user create to a list of locations
    public void checkLocation (String location, final FireBaseCallback fireBaseCallback) {
        Query query = getDatabaseReference().child("locations").orderByChild("Name").equalTo(location);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fireBaseCallback.onCallback(dataSnapshot.exists());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
            }
        });
    }

    public void storeUser (String email, final FireBaseCallback2 fireBaseCallback) {
        Query query = getDatabaseReference().child("users").orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        fireBaseCallback.onCallback(issue.child("location").getValue().toString());
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
            }
        });
    }


//    public void pushDummyDonationData(){
//        Location atlanta = new Location(1, "AFD Station 4", 33.75416f, -84.37742f,
//                "309 EDGEWOOD AVE SE", "Atlanta", "GA", "30332", "Drop Off",
//                "(404) 555 - 3456", "www.afd04.atl.ga");
//
//        Donation donation1 = new Donation(atlanta, "Old shirt", "Old blue shirt",
//                12.15f, "Clothing", "A cool old blue shirt");
//
//        Donation donation2 = new Donation(atlanta, "Old shirt", "Old red shirt",
//                6.50f, "Clothing", "A cool old red shirt");
//
//        Donation donation3 = new Donation(atlanta, "Dope hat", "Hat with dope things on it",
//                16.50f, "Hat", "hat be dope");
//
//        Donation donation4 = new Donation(atlanta, "iphone X", "overpriced garbage",
//                9999.50f, "Electronics", "dont buy this lol");
//
//        Donation donation5 = new Donation(atlanta, "Toaster", "Old toaster",
//                5.50f, "Households", "toast malone");
//
//        getDatabaseReference().child("locations").child("0").child("Donations").push().setValue(donation1);
//        getDatabaseReference().child("locations").child("0").child("Donations").push().setValue(donation2);
//        getDatabaseReference().child("locations").child("0").child("Donations").push().setValue(donation3);
//        getDatabaseReference().child("locations").child("0").child("Donations").push().setValue(donation4);
//        getDatabaseReference().child("locations").child("0").child("Donations").push().setValue(donation5);
//    }




}
