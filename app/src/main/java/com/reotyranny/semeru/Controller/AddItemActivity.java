package com.reotyranny.semeru.Controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;
import com.reotyranny.semeru.Model.Donation;
import com.reotyranny.semeru.Model.Model;
import com.reotyranny.semeru.R;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddItemActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;

    String downloadPath = null;

    Uri imageUri;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    StorageReference storageRef = storage.getReference();

    private final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        TextView locationText = findViewById(R.id.locationText);
        locationText.setText(model.userLocation);

        constructSpinner();

        Button uploadBtn = findViewById(R.id.button_uploadImg);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
//                findViewById(R.id.button_Cancel).setEnabled(false);
//                findViewById(R.id.button_Confirm).setEnabled(false);
                startActivityForResult(intent, READ_REQUEST_CODE);

            }


        });

        Button cancelBtn = findViewById(R.id.button_Cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(AddItemActivity.this, HomeScreenActivity.class));
            }
        });

        Button confirmDonation = findViewById(R.id.button_Confirm);
        confirmDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = model.getRef().child(Model.LOCATIONS).orderByChild("name").
                        equalTo(model.userLocation);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("Database-Error", databaseError.getMessage());
                    }

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            DataSnapshot item = dataSnapshot.getChildren().iterator().next();
                            Donation donation = constructDonationObject();
                            String uid = model.getRef().child(Model.LOCATIONS).child(Objects
                                    .requireNonNull(item.getKey())).
                                    child("donations").push().getKey();

                            Map<String, Object> childUpdates = new HashMap<>();
                            childUpdates.put("/" + Model.LOCATIONS + "/" + item.getKey() +
                                    "/donations/" + uid, donation);
                            childUpdates.put("/" + Model.DONATIONS + "/" + uid, donation);
                            model.getRef().updateChildren(childUpdates);
                        } else {
                            Log.d("whatz", "nope location is currently" + model.userLocation);
                        }
                    }
                });

                final Uri file = Uri.fromFile(new File(imageUri.toString()));
                StorageReference imageRef = storageRef.child("images/" + file.getLastPathSegment());
                try {
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    UploadTask uploadTask = imageRef.putStream(imageStream);
                    findViewById(R.id.button_Cancel).setEnabled(false);
                    findViewById(R.id.button_Confirm).setEnabled(false);
                    // Register observers to listen for when the download is done or if it fails
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            findViewById(R.id.button_Cancel).setEnabled(true);
                            findViewById(R.id.button_Confirm).setEnabled(true);
                            Toast.makeText(AddItemActivity.this,
                                    "Upload error", Toast.LENGTH_LONG).show();
                            Log.d("uploadError", exception.toString());
                        }
                    }).addOnSuccessListener(new OnSuccessListener<TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            findViewById(R.id.button_Cancel).setEnabled(true);
                            findViewById(R.id.button_Confirm).setEnabled(true);
                            Toast.makeText(AddItemActivity.this,
                                    "Upload successful!", Toast.LENGTH_LONG).show();
                            storageRef.child("images/" + file.getLastPathSegment())
                                    .getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            // Got the download URL for 'users/me/profile.png'
                                            downloadPath = uri.toString();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Log.d("URIerror", exception.toString());
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(AddItemActivity.this, HomeScreenActivity.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
            Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            if (resultData != null) {
                imageUri = resultData.getData();

                try {
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    ImageView imView = findViewById(R.id.donationImageView);
                    imView.setImageBitmap(selectedImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private Donation constructDonationObject() {
        //Model Model = com.reotyranny.semeru.Model.Model.getInstance();
        String shortDes = ((EditText) findViewById(R.id.editText_Short)).getText().toString();
        String longDes = ((EditText) findViewById(R.id.editText_Full)).getText().toString();
        String value = ((EditText) findViewById(R.id.editText_Value)).getText().toString();
        String comments = ((EditText) findViewById(R.id.editText_Comments)).getText().toString();
        String imageUrl = downloadPath;
        Spinner spinner = findViewById(R.id.spinner_Category);

        String category = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
        String location = model.userLocation;
        return new Donation(location, shortDes, longDes, Float.parseFloat(value), category, comments, imageUrl);
    }

    private void constructSpinner() {
        Spinner spinner = findViewById(R.id.spinner_Category);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}


