package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.reotyranny.semeru.R;
import com.reotyranny.semeru.Model.*;


public class EmployeeRegistationScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registation_screen);
        final TempDatabase tempDB = TempDatabase.getInstance();

        Button registerButton = findViewById(R.id.button_Register);
        registerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // name
                EditText editTextName = findViewById(R.id.editText_Name);
                String Name = editTextName.getText().toString();
                // email
                EditText editTextEmail = findViewById(R.id.editText_Email);
                String LoginEmail = editTextEmail.getText().toString();
                // password
                EditText editTextPassword = findViewById(R.id.editText_Password);
                String LoginPassword = editTextPassword.getText().toString();
                // employee is the only type of user to register with a custom set of constructor
                // parameters (i.e. location is also passed to its constructor)
                EditText editTextLocation = findViewById(R.id.editText_Location);
                String Location = editTextLocation.getText().toString();

                tempDB.addToDatabase(new Employee(Name, LoginEmail, LoginPassword, LoginEmail, Location));

                startActivity(new Intent(EmployeeRegistationScreenActivity.this, WelcomeScreenActivity.class));

            }
        });


    }


}
