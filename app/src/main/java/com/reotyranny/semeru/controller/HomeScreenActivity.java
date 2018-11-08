package com.reotyranny.semeru.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.reotyranny.semeru.model.Model;
import com.reotyranny.semeru.R;
import java.util.Objects;

public class HomeScreenActivity extends AppCompatActivity {

    private final Model model = Model.getInstance();

    private final FirebaseAuth mAuth = model.getAuth();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button signOutButton = findViewById(R.id.button_SignOut);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setUserLocation(null);
                mAuth.signOut();
                startActivity(new Intent(HomeScreenActivity.this, WelcomeScreenActivity.class));
            }
        });

        Button map = findViewById(R.id.button_Map);
        map.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, MapsActivity.class));
            }
        });

        Button loadIn = findViewById(R.id.button_LoadIn);
        loadIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, LocationListActivity.class));
            }
        });

        Button search = findViewById(R.id.button_SearchPage);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, QueryActivity.class));
            }
        });

        //final TextView currentUserText = findViewById(R.id.currentUser_TextView);
        final TextView welcomeUserText = findViewById(R.id.text_Welcome);

        FirebaseUser user = model.getUser();

        if ((user != null) && (user.getEmail() != null)) {
            // User is signed in
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            Query query = reference.child(Model.USERS).orderByChild("email").equalTo(user.getEmail());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("Database-Error", databaseError.getMessage());
                }

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // query matches exactly to user.getEmail() -- just use 1 iteration
                        DataSnapshot item = dataSnapshot.getChildren().iterator().next();
                        String name = Objects.requireNonNull(item.child("name").getValue()).toString();
                        //String currentUserString = String.format(
                        //       getResources().getString(R.string.current_user), name);
                        String welcomeString = String.format(
                                getResources().getString(R.string.welcome_user), name);
                        //currentUserText.setText(currentUserString);
                        welcomeUserText.setText(welcomeString);
                    }
                }
            });
        }
        else {
            welcomeUserText.setText("Welcome Guest");
        }

    }

}
