package com.reotyranny.semeru.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.Model.Model;
import com.reotyranny.semeru.R;

import java.util.List;


public class LocationListActivity extends AppCompatActivity {

    List<Location> location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_specific);
        // ...
        // Lookup the recyclerview in activity layout
        RecyclerView rvLocation = (RecyclerView) findViewById(R.id.recycler_Locations);
        final Model mo = Model.getInstance();
        // Initialize contacts
        location = mo.places;
        // Create adapter passing in the sample user data
        LocationAdapter adapter = new LocationAdapter(location);
        // Attach the adapter to the recyclerview to populate items
        rvLocation.setAdapter(adapter);
        // Set layout manager to position the items
        rvLocation.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }
}
