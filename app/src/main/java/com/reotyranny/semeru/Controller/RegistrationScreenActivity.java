package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.reotyranny.semeru.Model.*;
import com.reotyranny.semeru.R;

public class RegistrationScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        final AccountType a = (AccountType) getIntent().getSerializableExtra("type");


        Button registerButton = findViewById(R.id.button_Register);
        registerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
				EditText editTextName = findViewById(R.id.editText_Name);
				String Name = editTextName.getText().toString();
				// email
				EditText editTextEmail = findViewById(R.id.editText_Email);
				String LoginEmail = editTextEmail.getText().toString();

				// password
				EditText editTextPassword = findViewById(R.id.editText_Password);
				String LoginPassword = editTextPassword.getText().toString();

            	if (!a.equals(AccountType.employee)) {
					EditText editTextLocation = findViewById(R.id.editText_Location);
					String Location = editTextEmail.getText().toString();

					Employee newRegister = new Employee(Name, LoginEmail, LoginPassword, LoginEmail, Location);

				}

				else {
            		if (a.equals(AccountType.administrator)) {
						Admin newRegister = new Admin(Name, LoginEmail, LoginPassword, LoginEmail);
					}
					else if (a.equals(AccountType.manager)) {
            			//
					}


				}


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
