package com.example.mastro.selfit;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends OriginalFragment implements View.OnClickListener{

    CommBridge Bridge;
    User user;
    ImageView imageView;



    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        user = ((MainActivity)getActivity()).getUser();
        Bridge = (CommBridge) getActivity();

    }

    public void update(User user){

        this.user = user;

        TextView name =(TextView) getActivity().findViewById(R.id.nameHolder);

        updatePicture();

        updateView(name, user);
    }

    public void updateView(TextView name, User user) {

        name.setText(user.getName());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        TextView name;
        TextView follow;
        TextView week;
        TextView edit;
        TextView gym;
        TextView _logout;
        //ProgressBar progressBar;

        follow =(TextView) rootView.findViewById(R.id.follow);
        week =(TextView) rootView.findViewById(R.id.week);
        gym =(TextView) rootView.findViewById(R.id.gym);
        name =(TextView) rootView.findViewById(R.id.nameHolder);
        edit = (TextView) rootView.findViewById(R.id.editProfile);
        _logout = (TextView) rootView.findViewById(R.id.buttonFrag);
        imageView = (ImageView) rootView.findViewById(R.id.profileImage);


        _logout.setOnClickListener(this);

        week.setOnClickListener(this);
        gym.setOnClickListener(this);
        edit.setOnClickListener(this);
        follow.setOnClickListener(this);

        updatePicture();
        updateView(name, user);


        return rootView;

    }

    @Override
    public void onClick(View view){

        Bridge.respond(view.getId());

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){



    }

    public void progressDismiss(){

        //ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_bar_profile);
        //progressBar.setVisibility(View.GONE);



    }

    public void updatePicture(){

        if(user.profileImageUrl == null) {

            Glide.with(this)
                    .load(R.drawable.profile).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).centerCrop().placeholder(R.drawable.profile).error(R.drawable.profile)
                    .into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            });

        }else{

            StorageReference storage = FirebaseStorage.getInstance().getReference().child("userProfile").child(user.userID).child("profilePicture.jpg");

            Glide.with(this)
                    .using(new FirebaseImageLoader())
                    .load(storage).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).centerCrop().placeholder(R.drawable.profile).error(R.drawable.profile)
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });
           /* Glide.with(this)
                    .using(new FirebaseImageLoader())
                    .load(storage).asBitmap().centerCrop().placeholder(R.drawable.profile).error(R.drawable.profile)
                    .into(new BitmapImageViewTarget(imageView){

                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }

                    });*/
        }


    }

//TODO persistencia con firebase
    public void changePic(Uri uri) {

        Glide.with(this)
                .load(uri).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).centerCrop().placeholder(R.drawable.profile).error(R.drawable.profile)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }
}
