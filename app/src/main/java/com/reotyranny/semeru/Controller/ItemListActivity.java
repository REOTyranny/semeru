package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
import com.reotyranny.semeru.Model.Donation;
import com.reotyranny.semeru.R;
import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {

    // --Commented out by Inspection (10/28/18, 11:29):List<Donation> items;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        mRecyclerView = findViewById(R.id.rvItems);

        final int locationKey = (int) getIntent().getSerializableExtra("locationKey");
        // use a linear layout manager
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("locations/" + locationKey + "/donations").orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
            }

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ArrayList<Donation> items = new ArrayList<>();
                final ArrayList<String> itemKeys = new ArrayList<>();
                for (DataSnapshot issue : dataSnapshot.getChildren()) {
                    Donation donation = issue.getValue(Donation.class);
                    items.add(donation);
                    itemKeys.add(issue.getKey());
                }
                mAdapter = new ItemAdapter(items, itemKeys, locationKey);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemListActivity.this, LocationListActivity.class));
            }
        });

        FloatingActionButton addNewDonation = findViewById(R.id.button_Add);
        addNewDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemListActivity.this, AddItemActivity.class));
            }
        });

    }
}
