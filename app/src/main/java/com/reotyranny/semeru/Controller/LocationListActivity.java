package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.reotyranny.semeru.Model.AccountType;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.Model.Model;
import com.reotyranny.semeru.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
        mRecyclerView = (RecyclerView) findViewById(R.id.rvLocation);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Model mo = Model.getInstance();
        location = mo.places;

        // specify an adapter (see also next example)
        mAdapter = new LocationAdapter(location);
        mRecyclerView.setAdapter(mAdapter);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationListActivity.this, HomeScreenActivity.class));
            }
        });
    }

}
