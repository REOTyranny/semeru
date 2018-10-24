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
        final int key = (int) getIntent().getSerializableExtra("key");

        FirebaseModel FB = FirebaseModel.getInstance();
        Query query = FB.getDatabaseReference().child("locations2").orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String key = getIntent().getSerializableExtra("key").toString();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if (issue.getKey().equals(key)) {
                            Log.d("abc", issue.toString());
                            TextView name = findViewById(R.id.text_LocName);
                            name.setText(issue.child("name").getValue().toString());

                            TextView type = findViewById(R.id.text_LocType);
                            type.setText(issue.child("type").getValue().toString());

                            TextView longe = findViewById(R.id.text_Long);
                            longe.setText(issue.child("longitude").getValue().toString());

                            TextView lati = findViewById(R.id.text_Lat);
                            lati.setText(issue.child("latitude").getValue().toString());

                            TextView address = findViewById(R.id.text_Address);
                            address.setText(issue.child("address").getValue().toString());

                            TextView phone = findViewById(R.id.text_Phone);
                            phone.setText(issue.child("phone").getValue().toString());

                            String f = issue.child("name").getValue().toString();
                            if ( FirebaseModel.getInstance().userLocation.equals(f)) {
                                Button seeItems = findViewById(R.id.button_SeeItems);
                                seeItems.setVisibility(View.VISIBLE); //To set visible
                            }
                        }
                        else {
                            Log.d("abc", "nope!");
                        }
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
                intent.putExtra("key", key);
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
}
