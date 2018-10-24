package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.reotyranny.semeru.Model.Donation;
import com.reotyranny.semeru.Model.FirebaseModel;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        FirebaseModel Model = FirebaseModel.getInstance();


        TextView locationText = findViewById(R.id.locationText);
        locationText.setText(Model.userLocation);

        Button cancelBtn = findViewById(R.id.button_Cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(AddItemActivity.this, HomeScreenActivity.class));
            }
        });


        Button confirmDonation =  findViewById(R.id.button_Confirm);
        confirmDonation.setOnClickListener( new View.OnClickListener() {
            FirebaseModel Model = FirebaseModel.getInstance();
            @Override
            public void onClick(View v) {


                Query query = Model.getDatabaseReference().child("locations").orderByChild("Name");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    String shortDes = ((EditText)findViewById(R.id.editText_Short)).getText().toString();
                    String longDes = ((EditText)findViewById(R.id.editText_Full)).getText().toString();
                    String value = ((EditText)findViewById(R.id.editText_Value)).getText().toString();
                    String comments = ((EditText)findViewById(R.id.editText_Comments)).getText().toString();

                    Location location = new Location(12, Model.userLocation, 1.0f, 2.0f, "test address",
                            "test-city", "test-state", "102", "Some type", "404 1234 123", "www.test.com");

                    Donation donation = new Donation(location, shortDes, longDes, Float.parseFloat(value), "Kitchen", comments);
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot issue : dataSnapshot.getChildren()) {
                                if (issue.child("Name").getValue().toString().equals(Model.userLocation)) {
                                    Model.getDatabaseReference().child("locations").child(issue.getKey()).child("Donations").push().setValue(donation);
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                startActivity(new Intent(AddItemActivity.this, HomeScreenActivity.class));
            }
        });
    }
}


