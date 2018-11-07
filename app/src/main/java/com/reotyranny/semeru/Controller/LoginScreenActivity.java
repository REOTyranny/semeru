package com.reotyranny.semeru.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.reotyranny.semeru.Model.Model;
import com.reotyranny.semeru.R;
import java.util.Date;

public class LoginScreenActivity extends AppCompatActivity {

    private static final int MAX_ATTEMPTS = 3;

    private final Model model = Model.getInstance();

    private final FirebaseAuth mAuth = model.getAuth();

    private int userAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        final Button confirmButton = findViewById(R.id.button_Confirm);

        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String email = ((EditText) findViewById(R.id.editText_Email)).getText().toString();
                final String password = ((EditText) findViewById(R.id.editText_Password)).getText().toString();

                if (!TextUtils.isEmpty(password) && (password.length() >= 6) && isValidEmail(email)) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                            LoginScreenActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        userAttempts++;
                                        if (userAttempts > MAX_ATTEMPTS) {
                                            startLockout(confirmButton);
                                            Toast.makeText(LoginScreenActivity.this,
                                                    "Maximum login attempts reached, try again in 5 minutes.",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(LoginScreenActivity.this,
                                                    "Sign in error", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(LoginScreenActivity.this,
                                                "Login Successful", Toast.LENGTH_SHORT).show();
                                        String uid = model.getUser().getUid();
                                        // Model.FireBaseCallback is used here instead
                                        // of model.FireBaseCallback because it is an interface!
                                        model.storeUser(uid, new Model.FireBaseCallback() {
                                            @Override
                                            public void onCallback(String location) {
                                                model.setUserLocation(location);
                                                startActivity(new Intent(
                                                        LoginScreenActivity.this, HomeScreenActivity.class));
                                            }
                                        });

                                    }
                                }
                            });
                } else {
                    Toast.makeText(LoginScreenActivity.this,
                            "Invalid e-mail or password", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button cancelButton = findViewById(R.id.button_Cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreenActivity.this, WelcomeScreenActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Button confirmButton = findViewById(R.id.button_Confirm);
        verifyLockout(confirmButton);
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && email.matches(
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    private void startLockout(final Button confirmButton) {
        SharedPreferences prefs = this.getSharedPreferences("time", Context.MODE_PRIVATE);
        long currentTime = new Date().getTime();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("time", currentTime);
        editor.apply();
        confirmButton.setEnabled(false);
    }

    private void verifyLockout(final Button confirmButton) {
        SharedPreferences prefs = this.getSharedPreferences("time", Context.MODE_PRIVATE);
        long previousTime = prefs.getLong("time", 0);
        long currentTime = new Date().getTime();
        final long ONE_MINUTE = 60 * 1000;
        // 5*60*1000 = 5 min, each with 60 sec, each with 1000 millisec
        if ((currentTime - previousTime) > (5 * ONE_MINUTE)) {
            //enable the button
            confirmButton.setEnabled(true);
        } else {
            //disable it and start a new CountdownTimer; this is needed in order for
            //it to to become enabled if you're still in the app and the time ran out
            confirmButton.setEnabled(false);
            new CountDownTimer(currentTime - previousTime, 1000) {
                @Override
                public void onFinish() {
                    confirmButton.setEnabled(true);
                }

                @Override
                public void onTick(long millisUntilFinished) {
                    //confirmButton.setText("" + millisUntilFinished / 1000);
                }
            }.start();
        }
    }

}
