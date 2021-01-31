package com.android.kevng2.freestuff.fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.kevng2.freestuff.R;

import timber.log.Timber;

public class ListingFragment extends Fragment {

    ImageView ivPhoto;
    Button btnUpload;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listing, container, false);

        //Spinner
        Spinner spinner = view.findViewById(R.id.spinnerCondition);
        ArrayAdapter<CharSequence> conditions = ArrayAdapter.createFromResource(getContext(),R.array.conditions,android.R.layout.simple_spinner_item);
        conditions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(conditions);

        //Uploading the image
        btnUpload = view.findViewById(R.id.btnUpload);

        return view;
    }
}