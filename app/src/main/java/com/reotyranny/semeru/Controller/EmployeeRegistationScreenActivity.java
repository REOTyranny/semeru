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
        final Model model = Model.getInstance();

        Button registerButton = findViewById(R.id.button_Register);
        registerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String Name = ((EditText) findViewById(R.id.editText_Name)).getText().toString();
                String LoginEmail = ((EditText) findViewById(R.id.editText_Email)).getText().toString();
                String LoginPassword = ((EditText) findViewById(R.id.editText_Password)).getText().toString();

                boolean correctLocation = false;
                String LocationName = ((EditText) findViewById(R.id.editText_Location)).getText().toString();
                for (Location location : model.places) {
                    if (LocationName.equals(location.getName())) {
                        model.addAccount(new Employee(Name, LoginEmail, LoginPassword, LoginEmail, location));
                        correctLocation = true;
                    }
                }
                // invalid location ? --> just give in first location in model
                if (!correctLocation) model.addAccount(new Employee(Name, LoginEmail, LoginPassword, LoginEmail, model.places.get(0)));

                startActivity(new Intent(EmployeeRegistationScreenActivity.this, WelcomeScreenActivity.class));

            }
        });

        Button loginButton = findViewById(R.id.button_Cancel);
        loginButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeRegistationScreenActivity.this, WelcomeScreenActivity.class));
            }
        });


    }


}
