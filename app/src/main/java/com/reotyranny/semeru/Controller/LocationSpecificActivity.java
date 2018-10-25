package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.reotyranny.semeru.Model.FirebaseModel;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

public class LocationSpecificActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_specific);
        final int locationKey = (int) getIntent().getSerializableExtra("locationKey");

        FirebaseModel FB = FirebaseModel.getInstance();
        Query query = FB.getDatabaseReference().child("locations2").child(""+locationKey);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Location l = dataSnapshot.getValue(Location.class);
                    populateFields(l);
                    String specificLocation = l.getName();
                    if ( FirebaseModel.getInstance().userLocation.equals(specificLocation)) {
                                Button seeItems = findViewById(R.id.button_SeeItems);
                                seeItems.setVisibility(View.VISIBLE); //To set visible
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
            }
        });
        
        Button itemListButton = findViewById(R.id.button_SeeItems);
        itemListButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LocationSpecificActivity.this, ItemListActivity.class);
                intent.putExtra("locationKey", locationKey);
                v.getContext().startActivity(intent);
            }
        });


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationSpecificActivity.this, LocationListActivity.class));
            }
        });


    }
    public void populateFields(Location l) {
        ((TextView)findViewById(R.id.text_LocName)).setText(l.getName());
        ((TextView) findViewById(R.id.text_LocType)).setText(l.getType());
        ((TextView) findViewById(R.id.text_Long)).setText("" + l.getLongitude());
        ((TextView) findViewById(R.id.text_Lat)).setText("" + l.getLatitude());
        ((TextView) findViewById(R.id.text_Address)).setText(l.getAddress());
        ((TextView) findViewById(R.id.text_Phone)).setText(l.getPhone());
    }

}
