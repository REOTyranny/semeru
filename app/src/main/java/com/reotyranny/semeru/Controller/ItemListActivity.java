package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.reotyranny.semeru.Model.Donation;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    List<Donation> items;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        mRecyclerView = findViewById(R.id.rvItems);
       // final int key = (int) getIntent().getSerializableExtra("key");
        final String keyS = getIntent().getSerializableExtra("key").toString();

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //Model model = Model.getInstance();
        //Location location = model.places.get(key);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("locations").child(keyS).child("Donations").orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Donation> items = new ArrayList<>();
//                final String key = getIntent().getSerializableExtra("key").toString();
                final int key = (int) getIntent().getSerializableExtra("key");

                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        String shortDes = issue.child("shortDes").getValue().toString();
                        String longDes = issue.child("longDes").getValue().toString();
                        float value = ((Number) issue.child("value").getValue()).floatValue();
                        String category = issue.child("category").getValue().toString();
                        String comments = issue.child("comments").getValue().toString();
                        issue = issue.child("place");
                        long keyf = (long) issue.child("key").getValue();
                        String name = (String) issue.child("name").getValue();
                        float longitude = ((Number) issue.child("longitude").getValue()).floatValue();
                        float latitude = ((Number) issue.child("latitude").getValue()).floatValue();
                        String address = (String) issue.child("address").getValue();
                        String city = (String) issue.child("city").getValue();
                        String state = (String) issue.child("state").getValue();
                        String zip = issue.child("zip").getValue().toString();
                        String type = (String) issue.child("type").getValue();
                        String phone = (String) issue.child("phone").getValue();
                        String website = (String) issue.child("website").getValue();
                        Location location = new Location(keyf, name, longitude, latitude, address,
                                city, state, zip, type, phone, website);


                        items.add(new Donation(location, shortDes,longDes, value, category, comments));

                    }
                }
                // specify an adapter (see also next example)
                mAdapter = new ItemAdapter(items, key);
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
                startActivity(new Intent(ItemListActivity.this, LocationListActivity.class));
            }
        });


        FloatingActionButton addNewDonation =  findViewById(R.id.button_Add);
        addNewDonation.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemListActivity.this, AddItemActivity.class));
            }
        });
    }
}
