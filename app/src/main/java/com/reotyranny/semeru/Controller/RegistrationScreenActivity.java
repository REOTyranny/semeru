package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.reotyranny.semeru.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationScreenActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        mAuth = FirebaseAuth.getInstance();

        Button registerButton = findViewById(R.id.button_Register);
        registerButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //String name = ((EditText)findViewById(R.id.editText_Name)).getText().toString();
                String email = ((EditText)findViewById(R.id.editText_Email)).getText().toString();
                String password = ((EditText)findViewById(R.id.editText_Password)).getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        RegistrationScreenActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegistrationScreenActivity.this,
                                            "Password too short", Toast.LENGTH_SHORT).show();
                                } else
                                    startActivity(new Intent(
                                            RegistrationScreenActivity.this, HomeScreenActivity.class));
                            }
                        });

                startActivity(new Intent(RegistrationScreenActivity.this, WelcomeScreenActivity.class));

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

}
