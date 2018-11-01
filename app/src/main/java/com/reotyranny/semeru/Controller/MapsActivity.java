package com.reotyranny.semeru.Controller;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.Model.Model;
import com.reotyranny.semeru.R;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private final Model model = Model.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera


        Query query = model.getRef().child(Model.LOCATIONS).orderByChild("key");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Databaser-Error", databaseError.getMessage());
            }

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Location> locations = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    double locSum = 0;
                    double latSum = 0;
                    int total = 0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Location l = snapshot.getValue(Location.class);
                        locations.add(l);
                        double loc = l.getLongitude();
                        double lat = l.getLatitude();
                        total++;
                        locSum+=loc;
                        latSum+=lat;
                        LatLng a = new LatLng(lat, loc);
                        mMap.addMarker(new MarkerOptions().position(a).title(l.getName()));
                    }
                    LatLng cam = new LatLng(latSum/total, locSum/total);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(cam));
                }

            }
        });
    }
}
