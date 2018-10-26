package com.reotyranny.semeru.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.reotyranny.semeru.Model.Donation;
import com.reotyranny.semeru.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends
        RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    final ArrayList<String> mItemKeys;
    private int mLocationKey;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView descriptionTextView;
        public Button specificButton;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            descriptionTextView = itemView.findViewById(R.id.text_ShortDescription);
            specificButton = itemView.findViewById(R.id.button_Specfic);
        }
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View item = inflater.inflate(R.layout.recycler_view_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(item);
    }
    private List<Donation> mItem;
    // Pass in the contact array into the constructor
    public ItemAdapter(List<Donation> donation, ArrayList<String> keys, int locationKey) {
        mItemKeys = keys;
        mLocationKey = locationKey;
        if(donation == null){
            mItem = new ArrayList<>();
        }else {
            mItem = donation;
        }
    }
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Donation donation = mItem.get(position);

        // Set item views based on your views and data model
        TextView descriptionTextView = viewHolder.descriptionTextView;
        descriptionTextView.setText(donation.getShortDes());
        Button moreInfoButton = viewHolder.specificButton;
        moreInfoButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), SpecificItemActivity.class);
                int itemIndex = viewHolder.getAdapterPosition();
                intent.putExtra("itemKey", mItemKeys.get(itemIndex));
                // locationKey required so SpecificItemActivity can return to screen with
                // list of donations at said location
                intent.putExtra("locationKey", mLocationKey);
                v.getContext().startActivity(intent);
            }
        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mItem.size();
    }
}