package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

public class LocationSpecificActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_specific);
        final int key = (int) getIntent().getSerializableExtra("key");
        //Model model = Model.getInstance();
        //TODO
        Location location = null;

        Button itemListButton = findViewById(R.id.button_ItemsList);
        itemListButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LocationSpecificActivity.this, ItemListActivity.class);
                intent.putExtra("key", key);
                v.getContext().startActivity(intent);
            }
        });

        TextView name = findViewById(R.id.text_LocName);
        name.setText(location.getName());
        TextView type = findViewById(R.id.text_LocType);
        type.setText(location.getType());
        TextView longe = findViewById(R.id.text_Long);
        longe.setText(""+location.getLongitude());
        TextView lati = findViewById(R.id.text_Lat);
        lati.setText(""+location.getLatitude());
        TextView address = findViewById(R.id.text_Address);
        address.setText(location.getAddress());
        TextView phone = findViewById(R.id.text_Phone);
        phone.setText(location.getPhone());


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationSpecificActivity.this, LocationListActivity.class));
            }
        });
    }
}
