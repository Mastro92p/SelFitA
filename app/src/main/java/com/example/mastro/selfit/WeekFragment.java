package com.example.mastro.selfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mastro on 4/21/2017.
 */

public class WeekFragment extends OriginalFragment implements View.OnClickListener {

    CommBridge Bridge;

    // Listview Adapter
    ArrayAdapter<String> adapterJ;
    // SearchFragment EditText
    EditText inputSearch;
    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    private static final String TAG = "RecyclerViewExample";
    private List<FeedItem> feedsList;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter adapter;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_week, container, false);

        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        mRecyclerView.setHasFixedSize(true);
        // Listview Data
        String products[] = {"Dell Inspiron", "HTC OneX", "HTC Wildfire", "HTC Sense", "HTC XE",
                "iPhone 4S", "Samsung",
                "Samsung Galaxy", "MacBook Air", "Mac Mini", "MacBook Pro"};

        FillfeedsList(products);

        adapter = new MyRecyclerViewAdapter(getActivity().getBaseContext(),feedsList);
        mRecyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return rootView;


    }

    public void FillfeedsList(String list[]){


        feedsList = new ArrayList<>();

        for (int i = 0; i < list.length ; i++) {

            FeedItem feedItem = new FeedItem();
            feedItem.setTitle(list[i]);
            feedsList.add(feedItem);

        }


    }


    @Override
    public void onClick(View v) {

    }

}
