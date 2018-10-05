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
    }
}
