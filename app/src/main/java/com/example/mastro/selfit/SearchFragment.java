package com.example.mastro.selfit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends OriginalFragment {


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




    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

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

        adapter = new MyRecyclerViewAdapter(getActivity().getBaseContext(),feedsList);
        mRecyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){


        inflater.inflate(R.menu.searchmenu, menu);

        final MenuItem searchItem = menu.findItem(R.id.searchItem);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint(getText(R.string.Search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getContext(), R.string.Done, Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });
        


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
