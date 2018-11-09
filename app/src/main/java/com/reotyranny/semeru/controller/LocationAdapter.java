package com.reotyranny.semeru.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.reotyranny.semeru.R;
import com.reotyranny.semeru.model.Location;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapts locations into a recycler view.
 */
public class LocationAdapter extends
        RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView addressTextView;

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        final TextView nameTextView;

        final Button specificButton;

        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.text_Name);
            addressTextView = itemView.findViewById(R.id.text_Address);
            specificButton = itemView.findViewById(R.id.button_Specific);
        }
    }

    private final List<Location> mLocation;

    /**
     * Instantiates a location adapter.
     *
     * @param location a list of locations
     */
    public LocationAdapter(List<Location> location) {
        if (location == null) {
            mLocation = new ArrayList<>();
        } else {
            mLocation = location;
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mLocation.size();
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Location location = mLocation.get(position);

        // Set item views based on your views and data model
        TextView nameView = viewHolder.nameTextView;
        nameView.setText(location.getName());
        TextView addressView = viewHolder.addressTextView;
        addressView.setText(location.getAddress());
        Button moreInfoButton = viewHolder.specificButton;

        moreInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LocationSpecificActivity.class);
                intent.putExtra("locationKey", viewHolder.getAdapterPosition());
                v.getContext().startActivity(intent);
            }
        });

    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View locationView = inflater.inflate(R.layout.recycler_view_location, parent, false);

        // Return a new holder instance
        return new ViewHolder(locationView);
    }
}