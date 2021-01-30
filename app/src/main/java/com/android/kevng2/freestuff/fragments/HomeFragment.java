package com.android.kevng2.freestuff.fragments;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.kevng2.freestuff.Adapter;
import com.android.kevng2.freestuff.Item;
import com.android.kevng2.freestuff.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    RecyclerView recyclerView;
    // Keep the itemList static for caching purposes (not having to load all the images again
    // on loads after the first one
    static List<Item> itemList = new ArrayList<>();

    // Indicates whether the item list has been downloaded yet
    static boolean cached = false;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        // recyclerView = getActivity().findViewById(R.id.rvList);

        //Adapter adapter = new Adapter(getActivity(), mlist);
        //recyclerView.setAdapter(adapter);
        // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //initData();
        // If we already have the items cached in the itemList
        if (!cached) {
            // Populate items using the Realtime Database
            DatabaseReference itemsRef = database.getReference("item/");

            itemsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    List<Map> data = (List<Map>) dataSnapshot.getValue();

                    // Go through each of the items
                    for (Map<String, Object> item : data) {
                        String name = item.get("name").toString();
                        String condition = item.get("condition").toString();
                        String description = item.get("description").toString();
                        String imageFileName = item.get("image").toString();
                        String status = item.get("status").toString();
                        Double lat = Double.parseDouble(item.get("lat").toString());
                        Double lng = Double.parseDouble(item.get("lng").toString());

                        // Get the image from Firebase Cloud Storage
                        final Drawable[] image = new Drawable[1];
                        StorageReference imageRef = storage.getReference("items/" +
                                imageFileName);

                        final long ONE_MEGABYTE = 1024 * 1024;
                        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
                            image[0] = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                            itemList.add(new Item(name, condition, description, image[0],
                                    status, lat, lng));
                            recyclerView.setAdapter(new Adapter(itemList));
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Error", "DATABASE ERROR");
                }
            });
        }
        // If we already loaded the data don't load it again, just set the adapter to the list
        // we've already populated
        else recyclerView.setAdapter(new Adapter(itemList));

        cached = true;
        return view;
    }

    public List<Item> initData() {


//        mlist.add(new Item(R.drawable.a, "kettle", "just like brand new,Newly used", "Fine", "Available"));
//        mlist.add(new Item(R.drawable.b, "sdf", "just like brand new,Newly used", "Great", "Available"));
//        mlist.add(new Item(R.drawable.c, "as", "just like brand new,Newly used", "Rusty", "Available"));
//        mlist.add(new Item(R.drawable.d, "asf", "just like brand new,Newly used", "New", "Not Availabe"));
//        mlist.add(new Item(R.drawable.e, "asf", "just like brand new,Newly used", "Fine", "Available"));

        return itemList;
    }
}