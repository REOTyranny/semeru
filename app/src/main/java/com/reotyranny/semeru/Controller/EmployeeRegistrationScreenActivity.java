package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.reotyranny.semeru.Model.Account;
import com.reotyranny.semeru.Model.AccountType;
import com.reotyranny.semeru.Model.FirebaseModel;
import com.reotyranny.semeru.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EmployeeRegistrationScreenActivity extends AppCompatActivity {

    FirebaseModel FB = FirebaseModel.getInstance();

    //TODO: Avoid code repetition (DRY) from the other Registration Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration_screen);
        final AccountType acctType = (AccountType) getIntent().getSerializableExtra("type");

        Button registerButton = findViewById(R.id.button_Register);

        registerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = ((EditText)findViewById(R.id.editText_Name)).getText().toString();
                final String email = ((EditText)findViewById(R.id.editText_Email)).getText().toString();
                final String password = ((EditText)findViewById(R.id.editText_Password)).getText().toString();
                final String location = ((EditText) findViewById(R.id.editText_Location)).getText().toString();

                // check firebase DB for location
                FB.checkLocation(location, new FirebaseModel.FireBaseCallback() {
                    @Override
                    public void onCallback(boolean isValid) {
                        if (isValid) {
                            FB.getAuthInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                                    EmployeeRegistrationScreenActivity.this,
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (!task.isSuccessful()) {
                                                //TODO: Handle each type of login error
                                                Toast.makeText(EmployeeRegistrationScreenActivity.this,
                                                        "Login error - see log", Toast.LENGTH_LONG).show();
                                                Log.w("registration-errors", "signInWithEmail:failure", task.getException());
                                            } else {
                                                addDetails(name, email, acctType, location);
                                                Toast.makeText(EmployeeRegistrationScreenActivity.this,
                                                        "Registered successfully", Toast.LENGTH_LONG).show();

                                                startActivity(new Intent(
                                                        EmployeeRegistrationScreenActivity.this, HomeScreenActivity.class));
                                            }
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(EmployeeRegistrationScreenActivity.this,
                                    "Invalid Location", Toast.LENGTH_LONG).show();
                        }
                    }
                });
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

    private void addDetails(String name, String email, AccountType acctType, String location) {
        Account account = new Account(name, email, acctType, location);
        FB.getDatabaseReference().child("users").push().setValue(account);
        FB.storeUser(email, new FirebaseModel.FireBaseCallback2() {
            @Override
            public void onCallback(String location) {
                FirebaseModel.getInstance().userLocation = location;
                startActivity(new Intent(
                        EmployeeRegistrationScreenActivity.this, HomeScreenActivity.class));
            }
        });
    }



}
