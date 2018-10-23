package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

public class LocationSpecificActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // need to do 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_specific);
       final String key = getIntent().getSerializableExtra("key").toString();
        //Model model = Model.getInstance();

        //TODO: Integrate with Firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("locations");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("testt", "hmm?");
                try {
                    final String key = getIntent().getSerializableExtra("key").toString();
                }
                catch(Exception e ){
                    Log.d("testt", e.getMessage());
                }
                if (dataSnapshot.exists()) {
                    Log.d("locs", "hmm?");
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        if ( issue.getKey() == "0") {
                            Log.d("locs", "whattup " + issue.getKey());
                            Log.d("locs", issue.child("Zip").getValue().toString());
                        }

                    }
                }
                else{
                    Log.d("locs", "nope");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Location location = null;

//        TextView name = findViewById(R.id.text_LocName);
//        name.setText(location.getName());
//        TextView type = findViewById(R.id.text_LocType);
//        type.setText(location.getType());
//        TextView longe = findViewById(R.id.text_Long);
//        longe.setText(""+location.getLongitude());
//        TextView lati = findViewById(R.id.text_Lat);
//        lati.setText(""+location.getLatitude());
//        TextView address = findViewById(R.id.text_Address);
//        address.setText(location.getAddress());
//        TextView phone = findViewById(R.id.text_Phone);
//        phone.setText(location.getPhone());

        Button itemListButton = findViewById(R.id.button_ItemsList);
        itemListButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LocationSpecificActivity.this, ItemListActivity.class);
                intent.putExtra("key", key);
                v.getContext().startActivity(intent);
            }
        });


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationSpecificActivity.this, LocationListActivity.class));
            }
        });
    }
}
