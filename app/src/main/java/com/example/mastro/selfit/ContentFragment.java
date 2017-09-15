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
 * Created by Mastro on 4/20/2017.
 */

public class ContentFragment extends OriginalFragment implements View.OnClickListener  {

    CommBridge Bridge;
    // Listview Adapter
    ArrayAdapter<String> adapterJ;
    // SearchFragment EditText
    EditText inputSearch;
    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    private static final String TAG = "RecyclerViewExample";
    private List<FeedItem> feedsList;
    private ProgressBar progressBar;

    public ContentFragment(){



    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bridge = (CommBridge) getActivity();




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String products[] = {"Dell Inspiron", "HTC OneX", "HTC Wildfire", "HTC Sense", "HTC XE",
                "iPhone 4S", "Samsung",
                "Samsung Galaxy", "MacBook Air", "Mac Mini", "MacBook Pro"};


        if(!this.getFlag()){

            RecyclerView mRecyclerView  = (RecyclerView) rootView.findViewById(R.id.recycler_view);;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
            mRecyclerView.setHasFixedSize(true);


            ContentRecyclerViewAdapter adapter;
            FillfeedsList(products);

            adapter = new ContentRecyclerViewAdapter(getActivity().getBaseContext(),feedsList);
            mRecyclerView.setAdapter(adapter);


        }else{

            RecyclerView mRecyclerView  = (RecyclerView) rootView.findViewById(R.id.recycler_view);;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
            mRecyclerView.setHasFixedSize(true);


            MyRecyclerViewAdapter adapter;
            FillfeedsList(products);

            adapter = new MyRecyclerViewAdapter(getActivity().getBaseContext(),feedsList);
            mRecyclerView.setAdapter(adapter);


        }


        // Inflate the layout for this fragment
        return rootView;

    }


    @Override
    public void onClick(View v) {

    }


    public void FillfeedsList(String list[]){


        feedsList = new ArrayList<>();

        for (int i = 0; i < list.length ; i++) {

            FeedItem feedItem = new FeedItem();
            feedItem.setTitle(list[i]);
            feedsList.add(feedItem);

        }


    }


}

