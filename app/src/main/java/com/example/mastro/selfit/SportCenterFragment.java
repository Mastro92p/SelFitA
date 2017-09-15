package com.example.mastro.selfit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mastro on 4/6/2017.
 */

public class SportCenterFragment extends OriginalFragment implements View.OnClickListener {

    CommBridge Bridge;

    public SportCenterFragment() {
        // Required empty public constructor
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bridge = (CommBridge) getActivity();

        TextView clases = (TextView) getActivity().findViewById(R.id.classlink);
        TextView planes = (TextView) getActivity().findViewById(R.id.planlink);
        TextView contacto = (TextView) getActivity().findViewById(R.id.contactlink);
        TextView follow = (TextView) getActivity().findViewById(R.id.followUnfollow);

        clases.setOnClickListener(this);
        planes.setOnClickListener(this);

        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Centro deportivo numero aqui 00 123123")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        follow.setOnClickListener(this);




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        return inflater.inflate(R.layout.fragment_sportcenter, container, false);

    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){

        menu.clear();


    }

    @Override
    public void onClick(View view){

        Bridge.respond(view.getId());

        if(view.getId()==R.id.followUnfollow){

            Toast.makeText(view.getContext(), "follow", Toast.LENGTH_SHORT).show();

        }

    }


    public void progressDismiss(){

        //ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_bar_profile);
        //progressBar.setVisibility(View.GONE);



    }

}
