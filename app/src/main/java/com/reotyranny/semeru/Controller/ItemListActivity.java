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

        final String keyS = getIntent().getSerializableExtra("key").toString();

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("locations").child(keyS).child("Donations").orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Donation> items = new ArrayList<>();
                final int key = (int) getIntent().getSerializableExtra("key");

                if (dataSnapshot.exists()) {
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        Donation donation = issue.getValue(Donation.class);
                        items.add(donation);
                    }
                }

                mAdapter = new ItemAdapter(items, key);
                mRecyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
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
