package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import com.reotyranny.semeru.Model.User;

import com.reotyranny.semeru.R;

public class LoginScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        final User test_user = new User("test-user", "user", "pass", "test@gmail.com", 1 );

        Button registerButton = findViewById(R.id.button_Confirm);
        registerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // email (username)
                EditText editTextEmail = findViewById(R.id.editText_Email);
                String LoginEmail = editTextEmail.getText().toString();

                // password
                EditText editTextPassword = findViewById(R.id.editText_Password);
                String LoginPassword = editTextPassword.getText().toString();

                // incorrect login text
                TextView badLoginText = findViewById(R.id.badLoginTextView);


                if( test_user.getUsername().equals(LoginEmail) && test_user.getPassword().equals(LoginPassword) )
                    startActivity(new Intent(LoginScreenActivity.this, HomeScreenActivity.class));
                else
                    badLoginText.setVisibility(View.VISIBLE);


            }
        });

        Button loginButton =  findViewById(R.id.button_Cancel);
        loginButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreenActivity.this, WelcomeScreenActivity.class));
            }
        });
    }

}
