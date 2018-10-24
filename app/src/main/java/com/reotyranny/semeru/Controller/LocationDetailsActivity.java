package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

public class LocationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);
        final int key = (int) getIntent().getSerializableExtra("key");
        //Model model = Model.getInstance();
        //TODO: Integrate with Firebase
       String details = "";
//        for (Location location : model.places){
//            if (location.getKey() == key) {
//                details = String.format("Name: %s\n" + "Latitude: %s\n" + "Longitude: %s\n" +
//                                "Street Address: %s\n" + "City: %s\n" + "State: %s\n" +
//                                "Zip: %s\n" + "Type: %s\n" +"Phone: %s\n" +
//                                "Website: %s\n",
//                        location.getName(), location.getLatitude(), location.getLongitude(),
//                        location.getAddress(), location.getCity(), location.getState(),
//                        location.getZip(), location.getType(), location.getPhone(),
//                        location.getWebsite());
//            }
//        }

        TextView tv = findViewById(R.id.textView);
        tv.setText(details);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationDetailsActivity.this, LocationListActivity.class));
            }
        });

    }

}
