package com.android.kevng2.freestuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailActivity extends AppCompatActivity {
    private TextView txtTitle;
    private ImageView imgItem;
    private TextView txtStatus;
    private TextView txtDescription;
    private TextView txtCondition;
    private Button btnPickup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        imgItem = (ImageView) findViewById(R.id.imgItem);
        txtCondition = (TextView) findViewById(R.id.txtCondition);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        btnPickup = (Button) findViewById(R.id.btnPickup);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id", -1);
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
    }
}