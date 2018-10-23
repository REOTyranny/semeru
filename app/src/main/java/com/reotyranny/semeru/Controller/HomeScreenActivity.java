package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.reotyranny.semeru.R;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button signOutButton =  findViewById(R.id.button_SignOut);
        signOutButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, WelcomeScreenActivity.class));
            }
        });

        Button loadIn =  findViewById(R.id.button_LoadIn);
        loadIn.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, LocationListActivity.class));
            }
        });

        TextView currentUser = findViewById(R.id.currentUser_TextView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // User is signed in
//            String name = user.getDisplayName();
//            Log.d("testt", name);
//            currentUser.setText(user.getDisplayName());
        }


    }

}
