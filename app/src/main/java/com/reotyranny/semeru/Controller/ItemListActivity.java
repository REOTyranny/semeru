package com.reotyranny.semeru.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.reotyranny.semeru.Model.Donation;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.Model.Model;
import com.reotyranny.semeru.R;

import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    List<Donation> items;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvItems);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        final int key = (int) getIntent().getSerializableExtra("key");
        Model model = Model.getInstance();
        Location location = model.places.get(key);

        items = location.inventory;

        // specify an adapter (see also next example)
        mAdapter = new ItemAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
    }
}
