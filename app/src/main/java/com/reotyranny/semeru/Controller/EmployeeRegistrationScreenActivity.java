package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.reotyranny.semeru.Model.AccountType;
import com.reotyranny.semeru.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class EmployeeRegistrationScreenActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration_screen);
        final AccountType acctType = (AccountType) getIntent().getSerializableExtra("type");

        mAuth = FirebaseAuth.getInstance();
        firebaseDB = FirebaseDatabase.getInstance();

        Button registerButton = findViewById(R.id.button_Register);
        registerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = ((EditText)findViewById(R.id.editText_Name)).getText().toString();
                String email = ((EditText)findViewById(R.id.editText_Email)).getText().toString();
                String password = ((EditText)findViewById(R.id.editText_Password)).getText().toString();
                String location = ((EditText) findViewById(R.id.editText_Location)).getText().toString();
                //TODO: Validate location
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        EmployeeRegistrationScreenActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(EmployeeRegistrationScreenActivity.this,
                                            "Password too short", Toast.LENGTH_SHORT).show();
                                } else
                                    startActivity(new Intent(
                                            EmployeeRegistrationScreenActivity.this, HomeScreenActivity.class));
                            }
                        });
                DatabaseReference ref = firebaseDB.getReference().child("account").push();
                ref.child("name").setValue(name);
                ref.child("email").setValue(email);
                ref.child("role").setValue(acctType);
                ref.child("location").setValue(location);
                startActivity(new Intent(EmployeeRegistrationScreenActivity.this, WelcomeScreenActivity.class));

            }
        });

        Button cancelButton = findViewById(R.id.button_Cancel);
        cancelButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeRegistrationScreenActivity.this, WelcomeScreenActivity.class));
            }
        });


    }


}
