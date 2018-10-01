package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.reotyranny.semeru.Model.AccountType;
import com.reotyranny.semeru.R;

public class RegistrationScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        AccountType a = (AccountType) getIntent().getSerializableExtra("type");


        Button registerButton = findViewById(R.id.button_Register);
        registerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationScreenActivity.this, HomeScreenActivity.class));
            }
        });
        Button loginButton = findViewById(R.id.button_Cancel);
        loginButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationScreenActivity.this, WelcomeScreenActivity.class));
            }
        });
    }

}
