package com.example.mastro.selfit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.support.design.widget.TabLayout;

/**
 * Created by Mastro on 4/5/2017.
 */

public class DetailedClassActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedclass);

        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(intent.getStringExtra("tittle"));
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton location = (ImageButton) findViewById(R.id.locationButton);
        ImageButton info = (ImageButton) findViewById(R.id.infoButton);
        Button add = (Button) findViewById(R.id.addButton);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Location aqui CARACAS")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Info de la clase aqui CARAkistan")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),"Agregado *",Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        onBackPressed();
        System.out.println("SETTINGUPDOWNHERE!!!!!!!!!!!!!!!!!!");
        return super.onOptionsItemSelected(item);
    }

}
