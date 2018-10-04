package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import com.reotyranny.semeru.Model.*;
import com.reotyranny.semeru.R;

public class LoginScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        final Model model = Model.getInstance();

        Button registerButton = findViewById(R.id.button_Confirm);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // email, password, incorrect login text
                String LoginEmail = ((EditText) findViewById(R.id.editText_Email)).getText().toString();
                String LoginPassword = ((EditText) findViewById(R.id.editText_Password)).getText().toString();
                TextView badLoginText = findViewById(R.id.badLoginTextView);

                Account account = model.checkLogin(LoginEmail, LoginPassword);

                if (account != model.theNullAccount) {
                    model.setCurrentAccount(account);
                    startActivity(new Intent(LoginScreenActivity.this, HomeScreenActivity.class));
                } else {
                    badLoginText.setVisibility(View.VISIBLE);
                }
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
