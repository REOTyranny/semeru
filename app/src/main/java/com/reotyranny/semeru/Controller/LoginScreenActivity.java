package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.reotyranny.semeru.Model.FirebaseModel;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

public class LoginScreenActivity extends AppCompatActivity {

    FirebaseModel FB = FirebaseModel.getInstance();
    FirebaseAuth mAuth = FB.getAuthInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Button confirmButton = findViewById(R.id.button_Confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = ((EditText) findViewById(R.id.editText_Email)).getText().toString();
                String password = ((EditText) findViewById(R.id.editText_Password)).getText().toString();


                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                            LoginScreenActivity.this,
                            new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            String email = ((EditText) findViewById(R.id.editText_Email)).getText().toString();
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginScreenActivity.this,
                                        "Sign in error", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginScreenActivity.this,
                                        "Login Successful", Toast.LENGTH_SHORT).show();
                                FB.storeUser(email, new FirebaseModel.FireBaseCallback2() {
                                            @Override
                                            public void onCallback(String location) {
                                                FirebaseModel.getInstance().userLocation = location;
                                                Log.d("fff", "location is " + location);
                                                startActivity(new Intent(
                                                        LoginScreenActivity.this, HomeScreenActivity.class));
                                            }
                                        });

                            }
                        }
                    });
                }
                            }
        });

        Button cancelButton =  findViewById(R.id.button_Cancel);
        cancelButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreenActivity.this, WelcomeScreenActivity.class));
            }
        });
    }

}
