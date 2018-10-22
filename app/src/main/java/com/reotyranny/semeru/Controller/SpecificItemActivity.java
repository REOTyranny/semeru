package com.reotyranny.semeru.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.reotyranny.semeru.Model.Donation;
import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

public class SpecificItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_item);

        final int locationID = (int) getIntent().getSerializableExtra("locationKey");
        final int itemID = (int) getIntent().getSerializableExtra("itemKey");
        //Model model = Model.getInstance();
        Location location = null;
        Donation item = location.inventory.get(itemID);


        TextView shortDes = findViewById(R.id.text_Short);
        shortDes.setText(item.getShortDes());
        TextView time = findViewById(R.id.text_Full);
        time.setText(item.getFulltime());
        TextView value = findViewById(R.id.text_Value);
        value.setText(""+item.getValue());
        TextView loc = findViewById(R.id.text_Location);
        loc.setText(""+location.getName());
        TextView category = findViewById(R.id.text_Category);
        category.setText(item.getCategory());
        TextView comments = findViewById(R.id.text_Comments);
        comments.setText(item.getComments());
        TextView Timestamp = findViewById(R.id.text_Timestamp);
        Timestamp.setText(item.getTimeStamp());


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpecificItemActivity.this, LocationListActivity.class));
            }
        });
    }
}
