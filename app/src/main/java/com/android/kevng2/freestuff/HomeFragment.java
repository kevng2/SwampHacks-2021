package com.android.kevng2.freestuff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    List<Item> mlist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        recyclerView.setAdapter(new Adapter(initData()));

        return view;
    }

    public List<Item> initData() {
        mlist = new ArrayList<>();
        mlist.add(new Item(R.drawable.a, "kettle", "just like brand new,Newly used", "Fine", "Available"));
        mlist.add(new Item(R.drawable.b, "sdf", "just like brand new,Newly used", "Great", "Available"));
        mlist.add(new Item(R.drawable.c, "as", "just like brand new,Newly used", "Rusty", "Available"));
        mlist.add(new Item(R.drawable.d, "asf", "just like brand new,Newly used", "New", "Not Availabe"));
        mlist.add(new Item(R.drawable.e, "asf", "just like brand new,Newly used", "Fine", "Available"));

        return mlist;
    }
}