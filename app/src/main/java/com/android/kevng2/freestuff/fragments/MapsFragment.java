package com.android.kevng2.freestuff.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.kevng2.freestuff.Item;
import com.android.kevng2.freestuff.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            for (Item i : HomeFragment.itemList) {
                LatLng pos = new LatLng(i.getLat(), i.getLng());
                Bitmap bitmap = ((BitmapDrawable)i.getImage()).getBitmap();
                //googleMap.addMarker(new MarkerOptions()
                //        .position(pos)
                //        .title(i.getName())
                //        .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
                //LatLngBound will cover all your marker on Google Maps
                Bitmap.Config conf = Bitmap.Config.ARGB_8888;
                Bitmap bmp = Bitmap.createBitmap(250, 250, conf);
                Canvas canvas1 = new Canvas(bmp);
                // paint defines the text color, stroke width and size
                Paint color = new Paint();
                color.setTextSize(35);
                color.setFakeBoldText(true);
                color.setColor(Color.BLACK);

                // modify canvas
                canvas1.drawBitmap(bitmap, 0,0, color);
                //canvas1.drawText(i.getName(), 20, 30, color);

                // add marker to Map
                googleMap.addMarker(new MarkerOptions()
                        .position(pos)
                        .title(i.getName())
                        .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                        // Specifies the anchor to be at a particular point in the marker image.
                        .anchor(0.5f, 1));
                builder.include(pos); //Taking Point A (First LatLng)
                //builder.include(pos); //Taking Point B (Second LatLng)

            }
            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 200);
            googleMap.moveCamera(cu);
            //googleMap.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);

        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_maps, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}