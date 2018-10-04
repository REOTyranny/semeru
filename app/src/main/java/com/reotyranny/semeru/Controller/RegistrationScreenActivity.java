package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.reotyranny.semeru.Model.*;
import com.reotyranny.semeru.R;

public class RegistrationScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        final AccountType a = (AccountType) getIntent().getSerializableExtra("type");
        final Model model = Model.getInstance();


        Button registerButton = findViewById(R.id.button_Register);
        registerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // name, email, password
                String Name = ((EditText)findViewById(R.id.editText_Name)).getText().toString();
                String LoginEmail = ((EditText)findViewById(R.id.editText_Email)).getText().toString();
                String LoginPassword = ((EditText)findViewById(R.id.editText_Password)).getText().toString();

                // Using the given email as the username
                if (a == AccountType.administrator)
                    model.addAccount(new Admin(Name, LoginEmail, LoginPassword, LoginEmail));
                else if (a == AccountType.manager)
                    model.addAccount(new Manager(Name, LoginEmail, LoginPassword, LoginEmail));
                else if (a == AccountType.user)
                    model.addAccount(new User(Name, LoginEmail, LoginPassword, LoginEmail));

                startActivity(new Intent(RegistrationScreenActivity.this, WelcomeScreenActivity.class));

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
