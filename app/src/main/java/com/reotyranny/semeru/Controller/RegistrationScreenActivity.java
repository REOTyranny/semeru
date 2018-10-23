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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.reotyranny.semeru.Model.Account;
import com.reotyranny.semeru.Model.AccountType;
import com.reotyranny.semeru.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationScreenActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDB;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        final AccountType acctType = (AccountType) getIntent().getSerializableExtra("type");

        mAuth = FirebaseAuth.getInstance();
        firebaseDB = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Button registerButton = findViewById(R.id.button_Register);

        registerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = ((EditText)findViewById(R.id.editText_Name)).getText().toString();
                final String email = ((EditText)findViewById(R.id.editText_Email)).getText().toString();
                final String password = ((EditText)findViewById(R.id.editText_Password)).getText().toString();
                Log.d("test-pass", "password is " + password);
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        RegistrationScreenActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    //TODO: Handle each type of login error
                                    Toast.makeText(RegistrationScreenActivity.this,
                                            "Login error - see log", Toast.LENGTH_LONG).show();
                                    Log.w("login-errors", "signInWithEmail:failure", task.getException());
                                } else {
                                    addDetails(name, email, acctType);
                                    Toast.makeText(RegistrationScreenActivity.this,
                                            "Registered successfully", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(
                                            RegistrationScreenActivity.this, HomeScreenActivity.class));
                                }
                            }
                        });
            }
        });

        Button cancelButton = findViewById(R.id.button_Cancel);
        cancelButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationScreenActivity.this, WelcomeScreenActivity.class));
            }
        });
    }

        private void addDetails(String name, String email, AccountType acctType) {
            Account account = new Account(name, email, acctType);
            DatabaseReference ref = firebaseDB.getReference();
            ref.child("users").push().setValue(account);
        }


}
