package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
    }

}
