package com.android.kevng2.freestuff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ItemDetailActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private int id;

    private TextView txtTitle, txtStatus, txtDescription, txtCondition;
    private ImageView imgItem;
    private Button btnPickup;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        database = FirebaseDatabase.getInstance();

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        imgItem = (ImageView) findViewById(R.id.imgItem);
        txtCondition = (TextView) findViewById(R.id.txtCondition);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        btnPickup = (Button) findViewById(R.id.btnPickup);
        mapView = (MapView) findViewById(R.id.mapView);

        Intent intent = getIntent();

        id = intent.getIntExtra("id", -1);
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        String condition = intent.getStringExtra("condition");
        String status = intent.getStringExtra("status");
        double lat = intent.getDoubleExtra("lat", -1);
        double lng = intent.getDoubleExtra("lng", -1);

        // Get Drawable image
        byte[] bytes = intent.getByteArrayExtra("image");
        BitmapDrawable[] image = new BitmapDrawable[1];
        image[0] = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(bytes, 0,
                bytes.length));

        txtTitle.setText(name);
        imgItem.setImageDrawable(image[0]);
        txtStatus.setText("Status: " + status);
        txtDescription.setText(description);
        txtCondition.setText("Condition: " + condition);

        if (!status.equals("Available")) btnPickup.setEnabled(false);

        // Forward to MapView
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng pos = new LatLng(lat, lng);
                googleMap.addMarker(new MarkerOptions()
                    .position(pos)
                );
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 14));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void btnClickPickup(View view) {
        DatabaseReference itemRef = database.getReference("item/" + String.valueOf(id) + "/");
        itemRef.child("status").setValue("Completed");
        btnPickup.setEnabled(false);
    }
}