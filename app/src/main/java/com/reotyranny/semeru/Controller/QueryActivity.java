package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.reotyranny.semeru.Model.AccountType;
import com.reotyranny.semeru.Model.Model;
import com.reotyranny.semeru.R;

import java.util.ArrayList;
import java.util.Objects;

public class QueryActivity extends AppCompatActivity {

    private final Model model = Model.getInstance();
    private static final int CATEGORY_RADIO_BUTTON = 0;
    private static final int NAME_RADIO_BUTTON = 1;
    private static final String ALL_LOCATIONS = "All locations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        final RadioGroup radioGroup = findViewById(R.id.radioGroup);

        Button backButton = findViewById(R.id.button_Back);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(QueryActivity.this, HomeScreenActivity.class));
            }
        });

        final Spinner spinner = findViewById(R.id.spinner2);
        final TextView searchText = findViewById(R.id.search_Text);

        // populate spinner
        model.getRef().child(Model.LOCATIONS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Database-Error", databaseError.getMessage());
            }

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ArrayList<String> locations = new ArrayList<>();
                locations.add(ALL_LOCATIONS);

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String locationName = Objects.requireNonNull(snapshot.child("name").getValue()).toString();
                    locations.add(locationName);
                }

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<>(QueryActivity.this,
                        android.R.layout.simple_spinner_item, locations);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(areasAdapter);
            }
        });

        Button searchButton = findViewById(R.id.button_Search);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(QueryActivity.this, ResultsActivity.class);

                int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
                String searchString = searchText.getText().toString();
                int locationKey = spinner.getSelectedItemPosition();

                if (index == CATEGORY_RADIO_BUTTON) {
                    i.putExtra("searchType", "category");
                }
                else { // index == NAME_RADIO_BUTTON
                    i.putExtra("searchType", "item");
                }

                i.putExtra("location", locationKey-1);
                i.putExtra("searchString", searchString);
                startActivity(i);
            }
        });




    }
}
