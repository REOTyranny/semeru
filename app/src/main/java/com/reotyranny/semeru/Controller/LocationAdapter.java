package com.reotyranny.semeru.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.reotyranny.semeru.Model.Location;
import com.reotyranny.semeru.R;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends
        RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView addressTextView;
        public Button specificButton;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.text_Name);
            addressTextView = (TextView) itemView.findViewById(R.id.text_Address);
            specificButton = (Button) itemView.findViewById(R.id.button_Specfic);
        }
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View locationView = inflater.inflate(R.layout.recycler_view_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(locationView);
        return viewHolder;
    }
    private List<Location> mLocation;
    // Pass in the contact array into the constructor
    public LocationAdapter(List<Location> location) {
        if(location == null){
            mLocation = new ArrayList<>();
        }else {
            mLocation = location;
        }
    }
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Location location= mLocation.get(position);

        // Set item views based on your views and data model
        TextView nameView = viewHolder.nameTextView;
        nameView.setText(location.getName());
        TextView addressView = viewHolder.addressTextView;
        addressView.setText(location.getAddress());
        Button button = viewHolder.specificButton;

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mLocation.size();
    }
}