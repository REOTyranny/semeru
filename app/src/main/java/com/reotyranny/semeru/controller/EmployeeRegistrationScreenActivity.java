package com.reotyranny.semeru.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.reotyranny.semeru.model.Account;
import com.reotyranny.semeru.model.AccountType;
import com.reotyranny.semeru.model.Model;
import com.reotyranny.semeru.R;
import java.util.ArrayList;
import java.util.Objects;

public class EmployeeRegistrationScreenActivity extends AppCompatActivity {

    private final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration_screen);
        final AccountType acctType = (AccountType) getIntent().getSerializableExtra("type");

        Button registerButton = findViewById(R.id.button_Register);
        final Spinner spinner = findViewById(R.id.locationSpinner);

        // populate spinner with locations in firebase db
        model.getRef().child(Model.LOCATIONS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
            }

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ArrayList<String> locations = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String locationName = Objects.requireNonNull(snapshot.child("name").getValue()).toString();
                    locations.add(locationName);
                }

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<>(EmployeeRegistrationScreenActivity.this,
                        android.R.layout.simple_spinner_item, locations);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(areasAdapter);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = ((EditText) findViewById(R.id.editText_Name)).getText().toString();
                final String email = ((EditText) findViewById(R.id.editText_Email)).getText().toString();
                final String password = ((EditText) findViewById(R.id.editText_Password)).getText().toString();
                final String location = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

                if (!TextUtils.isEmpty(password) && (password.length() >= 6) && isValidEmail(email)) {
                    model.getAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                            EmployeeRegistrationScreenActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
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
                } else {
                    Toast.makeText(EmployeeRegistrationScreenActivity.this,
                            "Invalid e-mail or password", Toast.LENGTH_LONG).show();
                }


            }
        });

        Button cancelButton = findViewById(R.id.button_Cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeRegistrationScreenActivity.this, WelcomeScreenActivity.class));
            }
        });
    }

    private void addDetails(String name, String email, AccountType acctType, String location) {
        Account account = new Account(name, email, acctType, location);
        String uID = model.getUser().getUid();
        model.getRef().child(Model.USERS).child(uID).setValue(account);
        model.storeUser(uID, new Model.FireBaseCallback() {
            @Override
            public void onCallback(String location) {
                model.setUserLocation(location);
                startActivity(new Intent(
                        EmployeeRegistrationScreenActivity.this, HomeScreenActivity.class));
            }
        });
    }

    private boolean isValidEmail(String email) {
        //noinspection LongLine
        return !TextUtils.isEmpty(email) && email.matches(
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

}
