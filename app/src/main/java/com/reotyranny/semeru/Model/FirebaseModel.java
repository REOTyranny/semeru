package com.reotyranny.semeru.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseModel {

    public DatabaseReference getDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public FirebaseAuth getFirebaseAuthInstance() {
        return FirebaseAuth.getInstance();
    }

    public FirebaseUser getCurrentUser() {
        return  getFirebaseAuthInstance().getCurrentUser();
    }

    // make it public boolean (to check valid add)
    public void addAccount(String name, String email, AccountType acctType, String location){
        Account account = new Account(name, email, acctType, location);
        getDatabaseReference().child("users").push().setValue(account);
    }

    // TODO
    public void addLocation(){
    }

    // TODO
    public void queryFirebase (Query query) {
        //Query query = reference.child("users").orderByChild("email").equalTo(user.getEmail());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
