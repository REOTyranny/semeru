package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

import java.util.ArrayList;
import java.util.List;


public class LocationListActivity extends AppCompatActivity {

    List<Location> location;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        mRecyclerView = findViewById(R.id.rvLocation);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //TODO: Restore the way locations are pulled from Firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("locations").orderByChild("Key");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Location> locations = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // Should be able to do this :
                        // Location l = issue.getValue(Location.class);
                        long key = (long) issue.child("Key").getValue();
                        String name = (String) issue.child("Name").getValue();
                        float longitude = ((Number) issue.child("Longitude").getValue()).floatValue();
                        float latitude = ((Number) issue.child("Latitude").getValue()).floatValue();
                        String address = (String) issue.child("Address").getValue();
                        String city = (String) issue.child("City").getValue();
                        String state = (String) issue.child("State").getValue();
                        String zip = issue.child("Zip").getValue().toString();
                        String type = (String) issue.child("Type").getValue();
                        String phone = (String) issue.child("Phone").getValue();
                        String website = (String) issue.child("Website").getValue();
                        locations.add(new Location(key, name, longitude, latitude, address,
                                city, state, zip, type, phone, website));
                    }
                }
                // specify an adapter (see also next example)
                mAdapter = new LocationAdapter(locations);
                mRecyclerView.setAdapter(mAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationListActivity.this, HomeScreenActivity.class));
            }
        });
    }

}
