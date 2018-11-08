package com.reotyranny.semeru.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.reotyranny.semeru.model.Donation;
import com.reotyranny.semeru.model.Model;
import com.reotyranny.semeru.R;
import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {
    // --Commented out by Inspection (10/28/18, 11:29):List<Donation> items;

    private static final int ALL_LOCATIONS = -1; // first item in spinner

    private RecyclerView.Adapter mAdapter;

    private RecyclerView mRecyclerView;

    private final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mRecyclerView = findViewById(R.id.recycler_view_results);

        final int location = (int) getIntent().getSerializableExtra("location");
        final String searchType = (String) getIntent().getSerializableExtra("searchType");
        final String searchQuery = (String) getIntent().getSerializableExtra("searchString");
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        Query query;

        String queryField = "category".equals(searchType) ? "category" : "shortDes";

        if (location == ALL_LOCATIONS) {
            query = model.getRef().child(Model.DONATIONS).orderByChild(queryField).equalTo(searchQuery);
        } else {
            query = model.getRef().child(Model.LOCATIONS).child("" + location).child("donations").
                    orderByChild(queryField).equalTo(searchQuery);
        }

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
                if (!items.isEmpty()) {
                    findViewById(R.id.text_FailSearch).setVisibility(View.GONE);
                }

                mAdapter = new ItemAdapter(items, itemKeys, location, searchType, searchQuery); // unused location key
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        Button backButton = findViewById(R.id.button_Back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultsActivity.this, QueryActivity.class));
            }
        });

//        Button searchAgainButton = findViewById(R.id.button_SearchAgain);
//        searchAgainButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ResultsActivity.this, QueryActivity.class));
//            }
//        });
    }
}
