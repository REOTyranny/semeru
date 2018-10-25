package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.HashMap;
import java.util.Map;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        FirebaseModel FB = FirebaseModel.getInstance();

        TextView locationText = findViewById(R.id.locationText);
        locationText.setText(FB.userLocation);

        constructSpinner();

        Button cancelBtn = findViewById(R.id.button_Cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(AddItemActivity.this, HomeScreenActivity.class));
            }
        });


        Button confirmDonation =  findViewById(R.id.button_Confirm);
        confirmDonation.setOnClickListener( new View.OnClickListener() {
            FirebaseModel FB = FirebaseModel.getInstance();
            @Override
            public void onClick(View v) {
                Query query = FB.getDatabaseReference().child("locations2").orderByChild("name").
                        equalTo(FB.userLocation);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Log.d("wtf", dataSnapshot.toString());
                            DataSnapshot item = dataSnapshot.getChildren().iterator().next();
                            Donation donation = constructDonationObject();
                            String uid = FB.getDatabaseReference().child("locations2").child(item.getKey()).
                                    child("donations").push().getKey();

                            Map<String, Object> childUpdates = new HashMap<>();
                            childUpdates.put("/locations2/" + item.getKey() + "/donations/" + uid, donation);
                            childUpdates.put("/donations/" + uid, donation);
                            FB.getDatabaseReference().updateChildren(childUpdates);
                        }
                        else Log.d("whatz", "nope location is currently" + FB.userLocation);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("Database-Error", databaseError.getMessage());
                    }
                });
                startActivity(new Intent(AddItemActivity.this, HomeScreenActivity.class));
            }
        });
    }

    private Donation constructDonationObject(){
        FirebaseModel Model = FirebaseModel.getInstance();
        String shortDes = ((EditText)findViewById(R.id.editText_Short)).getText().toString();
        String longDes = ((EditText)findViewById(R.id.editText_Full)).getText().toString();
        String value = ((EditText)findViewById(R.id.editText_Value)).getText().toString();
        String comments = ((EditText)findViewById(R.id.editText_Comments)).getText().toString();
        Spinner spinner = (Spinner) findViewById(R.id.spinner_Category);
        String category = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
        String location = Model.userLocation;
        Donation donation = new Donation(location, shortDes, longDes, Float.parseFloat(value), category, comments);
        return donation;
    }

    private void constructSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.spinner_Category);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}


