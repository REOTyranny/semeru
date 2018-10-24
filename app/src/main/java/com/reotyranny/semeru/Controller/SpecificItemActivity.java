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

import java.util.Iterator;

public class SpecificItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_item);

        final int locationID = (int) getIntent().getSerializableExtra("locationKey");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("locations").child(((Integer)locationID).toString()).child("Donations").orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final int itemID = (int) getIntent().getSerializableExtra("itemKey");
                if (dataSnapshot.exists()) {
                    Iterator snapshot = dataSnapshot.getChildren().iterator();
                    Log.d("fab", "itemID: " + itemID);
                    for(int i = 0; i != itemID; i++) snapshot.next();
                    Donation donation = ((DataSnapshot) snapshot.next()).getValue(Donation.class);
                    populateFields(donation);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
            }
        });


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (SpecificItemActivity.this, ItemListActivity.class);
                intent.putExtra("key", locationID);
                v.getContext().startActivity(intent);
            }
        });
    }

    public void populateFields(Donation d) {
        TextView shortDesView = findViewById(R.id.text_Short);
        shortDesView.setText(d.getShortDes());
        TextView time = findViewById(R.id.text_Full);
        time.setText(d.getFulltime());
        TextView valueView = findViewById(R.id.text_Value);
        valueView.setText("" + d.getValue());
        TextView loc = findViewById(R.id.text_Location);
        loc.setText("" + d.getPlace());
        TextView categoryView = findViewById(R.id.text_Category);
        categoryView.setText(d.getCategory());
        TextView commentsView = findViewById(R.id.text_Comments);
        commentsView.setText(d.getComments());
        TextView Timestamp = findViewById(R.id.text_Timestamp);
        Timestamp.setText(d.getTimeStamp());
    }
}
