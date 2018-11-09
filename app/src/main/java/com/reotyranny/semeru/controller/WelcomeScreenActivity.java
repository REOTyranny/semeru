package com.reotyranny.semeru.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.reotyranny.semeru.R;

/**
 * Controller for the Welcome! view.
 */
public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Button signUpButton = findViewById(R.id.button_SignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this, AccountTypeActivity.class));
            }
        });

        Button loginButton = findViewById(R.id.button_Login);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this, LoginScreenActivity.class));
            }
        });

        Button guestButton = findViewById(R.id.button_Guest);
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this, HomeScreenActivity.class));
            }
        });

    }


}
