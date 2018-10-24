package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;

public class EmployeeRegistrationScreenActivity extends AppCompatActivity {

    FirebaseModel FB = FirebaseModel.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration_screen);
        final AccountType acctType = (AccountType) getIntent().getSerializableExtra("type");

        Button registerButton = findViewById(R.id.button_Register);
        final Spinner spinner = (Spinner) findViewById(R.id.locationSpinner);

        // populate spinner with locations in firebase db
        FB.getDatabaseReference().child("locations2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<String> locations = new ArrayList<String>();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String locationName = snapshot.child("Name").getValue().toString();
                    locations.add(locationName);
                }


                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(EmployeeRegistrationScreenActivity.this,
                        android.R.layout.simple_spinner_item, locations);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
            }
        });

        registerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = ((EditText)findViewById(R.id.editText_Name)).getText().toString();
                final String email = ((EditText)findViewById(R.id.editText_Email)).getText().toString();
                final String password = ((EditText)findViewById(R.id.editText_Password)).getText().toString();
                final String location = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();


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
        FB.getDatabaseReference().child("users2").push().setValue(account);
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
