package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.reotyranny.semeru.Model.Donation;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

//        Model mo = Model.getInstance();
//        if (mo.places.isEmpty())
//            readSDFile();

        Button signUpButton = findViewById(R.id.button_SignUp);
        signUpButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this, AccountTypeActivity.class));
            }
        });

        Button loginButton = findViewById(R.id.button_Login);
        loginButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreenActivity.this, LoginScreenActivity.class));
            }
        });
    }

    public static final int KEY = 0;
    public static final int NAME = 1;
    public static final int LATITUDE = 2;
    public static final int LONGITUDE = 3;
    public static final int STREET_ADDRESS = 4;
    public static final int CITY = 5;
    public static final int STATE = 6;
    public static final int ZIP = 7;
    public static final int TYPE = 8;
    public static final int PHONE = 9;
    public static final int WEBSITE = 10;

    private void readSDFile() {
        //TODO: This will no longer use a CSV, but will read from Firebase.
        //The Model singleton is no more as well.
        //Model model = Model.getInstance();

        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locations);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                Log.d("test-location", line);
                String[] tokens = line.split(",");
                int key = Integer.parseInt(tokens[KEY]);
                float latitude = Float.parseFloat(tokens[LATITUDE]);
                float longitude = Float.parseFloat(tokens[LONGITUDE]);
                //model.addLocation(new Location(key, tokens[NAME], longitude, latitude, tokens[STREET_ADDRESS],
                //        tokens[CITY], tokens[STATE], tokens[ZIP], tokens[TYPE], tokens[PHONE], tokens[WEBSITE]));
            }
            br.close();
        } catch (IOException e) {
            Log.e("test-location", "error reading assets", e);
        }

    }

}
