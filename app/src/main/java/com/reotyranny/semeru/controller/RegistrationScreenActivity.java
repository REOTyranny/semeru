package com.reotyranny.semeru.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.reotyranny.semeru.R;
import com.reotyranny.semeru.model.Account;
import com.reotyranny.semeru.model.AccountType;
import com.reotyranny.semeru.model.Model;

/**
 * Controller for the registration screen view.
 */
public class RegistrationScreenActivity extends AppCompatActivity {

    private final Model model = Model.getInstance();

    private final FirebaseAuth mAuth = model.getAuth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        final AccountType acctType = (AccountType) getIntent().getSerializableExtra("type");

        Button registerButton = findViewById(R.id.button_Register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = ((EditText) findViewById(R.id.editText_Name)).getText().toString();
                final String email = ((EditText) findViewById(R.id.editText_Email)).getText().toString();
                final String password = ((EditText) findViewById(R.id.editText_Password)).getText().toString();

                if (!TextUtils.isEmpty(password) && isValidPassword(password) && isValidEmail(email)) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                            RegistrationScreenActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegistrationScreenActivity.this,
                                                "Login error - see log", Toast.LENGTH_LONG).show();
                                        Log.w("registration-errors", "signInWithEmail:failure", task.getException());
                                    } else {
                                        addDetails(name, email, acctType);
                                        Toast.makeText(RegistrationScreenActivity.this,
                                                "Registered successfully", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(
                                                RegistrationScreenActivity.this, HomeScreenActivity.class));
                                    }
                                }
                            });
                } else {
                    Toast.makeText(RegistrationScreenActivity.this,
                            "Invalid e-mail or password", Toast.LENGTH_LONG).show();
                }


            }
        });

        Button cancelButton = findViewById(R.id.button_Cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationScreenActivity.this, WelcomeScreenActivity.class));
            }
        });
    }

    private void addDetails(String name, String email, AccountType acctType) {
        Account account = new Account(name, email, acctType);
        String userID = model.getUser().getUid();
        model.getRef().child(Model.USERS).child(userID).setValue(account);
    }

    @SuppressWarnings("LongLine")
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && email.matches(
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password)
                && (password.length() >= 6)
                && password.matches(
                "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }
}
