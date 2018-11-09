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
import com.reotyranny.semeru.model.Donation;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapts items into a proper format for view.
 */
public class ItemAdapter extends
        RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        final TextView descriptionTextView;

        final Button specificButton;

        ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            descriptionTextView = itemView.findViewById(R.id.text_ShortDescription);
            specificButton = itemView.findViewById(R.id.button_Specific);
        }
    }

    private final List<Donation> mItem;

    private final List<String> mItemKeys;

    private final int mLocation;

    private final int mLocationKey;

    private String mSearchString;

    private final String mSearchType;

    /**
     * Instantiates items for a view.
     *
     * @param donation    list of donations
     * @param keys        list of item keys
     * @param locationKey key of a Location
     */
    public ItemAdapter(List<Donation> donation, List<String> keys, int locationKey) {
        mItemKeys = keys;
        mLocationKey = locationKey;
        mSearchType = mSearchString;
        mLocation = -1;
        if (donation == null) {
            mItem = new ArrayList<>();
        } else {
            mItem = donation;
        }
    }

    /**
     * Instantiates items for a view.
     *
     * @param donation     list of donations
     * @param keys         list of item keys
     * @param location     a location key
     * @param searchType   type to search
     * @param searchString string to search
     */
    public ItemAdapter(List<Donation> donation, List<String> keys,
            int location, String searchType, String searchString) {
        mItemKeys = keys;
        mLocationKey = -1;
        mLocation = location;
        mSearchType = searchType;
        mSearchString = searchString;
        if (donation == null) {
            mItem = new ArrayList<>();
        } else {
            mItem = donation;
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mItem.size();
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Donation donation = mItem.get(position);

        // Set item views based on your views and data model
        TextView descriptionTextView = viewHolder.descriptionTextView;
        descriptionTextView.setText(donation.getShortDes());
        Button moreInfoButton = viewHolder.specificButton;
        moreInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SpecificItemActivity.class);
                int itemIndex = viewHolder.getAdapterPosition();
                intent.putExtra("itemKey", mItemKeys.get(itemIndex));
                // locationKey required so SpecificItemActivity can return to screen with
                // list of donations at said location
                intent.putExtra("locationKey", mLocationKey);

                intent.putExtra("location", mLocation);
                intent.putExtra("searchType", mSearchType);
                intent.putExtra("searchString", mSearchString);

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
        View item = inflater.inflate(R.layout.recycler_view_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(item);
    }
}