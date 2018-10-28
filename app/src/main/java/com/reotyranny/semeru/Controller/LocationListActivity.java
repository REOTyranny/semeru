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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.reotyranny.semeru.Model.Model;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

import java.util.ArrayList;
import java.util.List;


public class LocationListActivity extends AppCompatActivity {

    List<Location> location;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Model model = Model.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        mRecyclerView = findViewById(R.id.rvLocation);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Query query = model.getRef().child(model.LOCATIONS).orderByChild("key");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Location> locations = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Location l = snapshot.getValue(Location.class);
                        locations.add(l);
                    }
                }
                // specify an adapter (see also next example)
                mAdapter = new LocationAdapter(locations);
                mRecyclerView.setAdapter(mAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Databaser-Error", databaseError.getMessage());
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
