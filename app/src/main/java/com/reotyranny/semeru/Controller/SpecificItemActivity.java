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
                            Donation d = issue.getValue(Donation.class);
//                            Log.d("fab", test.getShortDes());
//                            Log.d("abcde", issue.toString());
//                            String shortDes = issue.child("shortDes").getValue().toString();
//                            String longDes = issue.child("longDes").getValue().toString();
//                            float value = ((Number) issue.child("value").getValue()).floatValue();
//                            String category = issue.child("category").getValue().toString();
//                            String comments = issue.child("comments").getValue().toString();
//                            String location = issue.child("place").getValue().toString();

                            //Donation item = new Donation(d.getPlace(), d.gets, longDes, value, category, comments);

                            TextView shortDesView = findViewById(R.id.text_Short);
                            shortDesView.setText(d.getShortDes());
                            TextView time = findViewById(R.id.text_Full);
                            time.setText(d.getFulltime());
                            TextView valueView = findViewById(R.id.text_Value);
                            valueView.setText(""+d.getValue());
                            TextView loc = findViewById(R.id.text_Location);
                            loc.setText(""+d.getPlace());
                            TextView categoryView = findViewById(R.id.text_Category);
                            categoryView.setText(d.getCategory());
                            TextView commentsView = findViewById(R.id.text_Comments);
                            commentsView.setText(d.getComments());
                            TextView Timestamp = findViewById(R.id.text_Timestamp);
                            Timestamp.setText(d.getTimeStamp());

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
