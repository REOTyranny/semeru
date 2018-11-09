package com.reotyranny.semeru.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.reotyranny.semeru.R;
import com.reotyranny.semeru.model.Donation;
import com.reotyranny.semeru.model.Model;
import java.util.Objects;

/**
 * Controller for the specific item view.
 */
public class SpecificItemActivity extends AppCompatActivity {

    private Bitmap bm;


    private final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_item);
        final int locationKey = (int) getIntent().getSerializableExtra("locationKey");

        final int location = (int) getIntent().getSerializableExtra("location");
        final String searchType = (String) getIntent().getSerializableExtra("searchType");
        final String searchQuery = (String) getIntent().getSerializableExtra("searchString");
        final String itemKey = (String) getIntent().getSerializableExtra("itemKey");
        DatabaseReference ref = model.getRef();

        final FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage.getReference("images/" + itemKey);

        Query query = ref.child(Model.DONATIONS).child(itemKey);
        final long MAX_MB = 1024 * 1024;
        storageRef.getBytes(MAX_MB).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                ImageView image = findViewById(R.id.donationImageSpecific);
                image.setImageBitmap(bm);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                exception.printStackTrace();
                Log.e("BytesError", "Error getting bytes", exception);
            }
        });

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
            }

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Donation donation = dataSnapshot.getValue(Donation.class);

                    populateFields(Objects.requireNonNull(donation));
                }
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if you came from search
                if (searchType != null) {
                    Intent intent = new Intent(SpecificItemActivity.this, ResultsActivity.class);
                    intent.putExtra("location", location);
                    intent.putExtra("searchType", searchType);
                    intent.putExtra("searchString", searchQuery);
                    v.getContext().startActivity(intent);
                }
                // otherwise you're a location employee
                else {
                    Intent intent = new Intent(SpecificItemActivity.this, ItemListActivity.class);
                    intent.putExtra("locationKey", locationKey);
                    v.getContext().startActivity(intent);
                }

            }
        });
    }


    @SuppressLint("SetTextI18n")
    private void populateFields(Donation d) {
        TextView shortDesView = findViewById(R.id.text_Short);
        shortDesView.setText("Short description: " + d.getShortDes());
        TextView time = findViewById(R.id.text_Full);
        time.setText("Time: " + d.getTimestamp());
        TextView valueView = findViewById(R.id.text_Value);
        valueView.setText("Value: " + d.getValue());
        TextView loc = findViewById(R.id.text_Location);
        loc.setText("Location: " + d.getPlace());
        TextView categoryView = findViewById(R.id.text_Category);
        categoryView.setText("Category: " + d.getCategory());
        TextView commentsView = findViewById(R.id.text_Comments);
        commentsView.setText("Comments: " + d.getComments());
//        TextView Timestamp = findViewById(R.id.text_Timestamp);
//        Timestamp.setText(d.getTimestamp());
    }
}
