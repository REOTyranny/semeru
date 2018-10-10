package com.reotyranny.semeru.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

        readSDFile();
        Model mo = Model.getInstance();
        location = mo.places;

        // specify an adapter (see also next example)
        mAdapter = new LocationAdapter(location);
        mRecyclerView.setAdapter(mAdapter);
    }


    public static final int KEY = 0;
    public static final int NAME = 1;
    public static final int LATITUDE = 2;
    public static final int LONGITUDE = 3;
    public static final int STREET_ADDRESS = 4;
    public static final int CITY = 5;
    public static final int STATE = 6;
    public static final int ZIP = 7;
    public static final int TYPE = 8;
    public static final int PHONE = 9;
    public static final int WEBSITE = 10;

    private void readSDFile() {

        Model model = Model.getInstance();

        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locations);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                Log.d("test-location", line);
                String[] tokens = line.split(",");
                int key = Integer.parseInt(tokens[KEY]);
                float latitude = Float.parseFloat(tokens[LATITUDE]);
                float longitude = Float.parseFloat(tokens[LONGITUDE]);
                model.addLocation(new Location(key, tokens[NAME], longitude, latitude, tokens[STREET_ADDRESS],
                        tokens[CITY], tokens[STATE], tokens[ZIP], tokens[TYPE], tokens[PHONE], tokens[WEBSITE]));
            }
            br.close();
        } catch (IOException e) {
            Log.e("test-location", "error reading assets", e);
        }

    }

}
