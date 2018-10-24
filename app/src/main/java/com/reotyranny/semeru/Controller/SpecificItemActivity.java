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
import com.reotyranny.semeru.Model.Donation;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

public class SpecificItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_item);

        final int locationID = (int) getIntent().getSerializableExtra("locationKey");
        final int itemIDz = (int) getIntent().getSerializableExtra("itemKey");


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("locations").child(((Integer)locationID).toString()).child("Donations").orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final int itemID = (int) getIntent().getSerializableExtra("itemKey");
                Log.d("item-key", ""+ itemID);
                int i = 0;
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    // user iterator instead (increment instead of getting each result
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        Log.d("item-key", "index: " + i);
                        if ( i == itemID) {
                            Log.d("abcde", issue.toString());
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

                            Donation item = new Donation(location, shortDes, longDes, value, category, comments);

                            TextView shortDesView = findViewById(R.id.text_Short);
                            shortDesView.setText(shortDes);
                            TextView time = findViewById(R.id.text_Full);
                            time.setText(item.getFulltime());
                            TextView valueView = findViewById(R.id.text_Value);
                            valueView.setText(""+item.getValue());
                            TextView loc = findViewById(R.id.text_Location);
                            loc.setText(""+location.getName());
                            TextView categoryView = findViewById(R.id.text_Category);
                            categoryView.setText(item.getCategory());
                            TextView commentsView = findViewById(R.id.text_Comments);
                            commentsView.setText(item.getComments());
                            TextView Timestamp = findViewById(R.id.text_Timestamp);
                            Timestamp.setText(item.getTimeStamp());

                            break;
                        }
                        else {
                            i = i + 1;
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SpecificItemActivity.this, ItemListActivity.class));
                Intent intent = new Intent (SpecificItemActivity.this, ItemListActivity.class);
                intent.putExtra("key", locationID);
                v.getContext().startActivity(intent);
            }
        });
    }
}
