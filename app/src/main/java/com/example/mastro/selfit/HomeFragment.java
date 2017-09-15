package com.example.mastro.selfit;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends OriginalFragment {


    private List<HomeItem> feedsList;
    private RecyclerView mRecyclerView;
    private HomeRecyclerViewAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        mRecyclerView.setHasFixedSize(true);
        // Listview Data
        String products[] = {"Dell Inspiron", "HTC OneX", "HTC Wildfire", "HTC Sense", "HTC XE",
                "iPhone 4S", "Samsung",
                "Samsung Galaxy", "MacBook Air", "Mac Mini", "MacBook Pro"};

        FillfeedsList(products);

        adapter = new HomeRecyclerViewAdapter(getActivity().getBaseContext(),feedsList);
        mRecyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return rootView;

    }


    public void FillfeedsList(String list[]){


        feedsList = new ArrayList<>();

        for (int i = 0; i < list.length ; i++) {

            HomeItem feedItem = new HomeItem();
            feedItem.setTitle(list[i]);
            feedsList.add(feedItem);

        }


    }

}
