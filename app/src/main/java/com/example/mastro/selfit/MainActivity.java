package com.example.mastro.selfit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.annotation.IdRes;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;



import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CommBridge {

    private User currentUser;
    public boolean flagTab = false;
    private static final int REQUEST_EDIT = 0;
    public String UserID;
    FragNavController mNavController;
    BottomBar bottomBar;

    private final int INDEX_HOME = FragNavController.TAB1;
    private final int INDEX_SEARCH = FragNavController.TAB2;
    private final int INDEX_PROFILE = FragNavController.TAB3;


    //Fragment home2 = new HomeFragment();

   // SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final OriginalFragment home = new HomeFragment();
        final OriginalFragment search = new SearchFragment();
        final OriginalFragment profile = new ProfileFragment();
        home.setTAG("Selfit");
        search.setTAG("SearchFragment");
        profile.setTAG("ProfileFragment");

        search.setHasOptionsMenu(true);

        List<Fragment> fragments = new ArrayList<>(3);

        fragments.add(home);
        fragments.add(search);
        fragments.add(profile);

        mNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.contentContainer).rootFragments(fragments).build();

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.selectTabAtPosition(INDEX_HOME);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentUser = new User();
        UserID = getIntent().getStringExtra("userID");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
        mDatabase.keepSynced(true);
        mDatabase.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DataSnapshot aux = dataSnapshot.child(UserID);
                currentUser = aux.getValue(User.class);
                currentUser.setUserID(UserID);

                refreshView();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getBaseContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }

        });


        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                tabSelected(tabId);

            }
        });


        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {

                tabReselected(tabId);

            }
        });



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNavController != null) {
            mNavController.onSaveInstanceState(outState);
        }
    }


    @Override
    public void onBackPressed() {

            // disable going back to LoginActivity

            if (!mNavController.isRootFragment()) {

                mNavController.popFragment();

                if(mNavController.isRootFragment()){

                    if(bottomBar.getCurrentTabId() == R.id.Home ){

                        getSupportActionBar().setTitle(R.string.app_name);

                    }else if(bottomBar.getCurrentTabId() == R.id.Search){

                        getSupportActionBar().setTitle(R.string.Search);

                    }else{

                        getSupportActionBar().setTitle(R.string.Profile);

                        ProfileFragment frag = (ProfileFragment) mNavController.getCurrentFrag();

                        if (frag != null) {

                            frag.update(currentUser);
                            frag.progressDismiss();

                        }


                    }

                }else{

                    OriginalFragment aux =(OriginalFragment) mNavController.getCurrentFrag();
                    getSupportActionBar().setTitle(aux.getTAG());

                }

            } else {

                moveTaskToBack(true);
            }


    }


    @Override
    public void respond() {

    }

    @Override
    public void respond(@IdRes int viewId) {


        if (viewId == R.id.editProfile) {

            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            intent.putExtra("user",this.currentUser);
            intent.putExtra("userID",UserID);
            startActivityForResult(intent,REQUEST_EDIT);

        }else if(viewId == R.id.week){

            //Toast.makeText(getBaseContext(), "week", Toast.LENGTH_LONG).show();
            getSupportActionBar().setTitle(R.string.Week);
            OriginalFragment aux = new WeekFragment();
            aux.setTAG("Mi Semana");

            this.mNavController.pushFragment(aux);



        }else if(viewId == R.id.gym){

            getSupportActionBar().setTitle(R.string.Gym);
            OriginalFragment aux = new ContentFragment();
            aux.setTAG("Mis Establecimientos");
            aux.setFlag(true);

            this.mNavController.pushFragment(aux);

        }else if(viewId == R.id.follow){

            getSupportActionBar().setTitle(R.string.follow);
            OriginalFragment aux = new ContentFragment();
            aux.setTAG("Seguidos");
            aux.setFlag(true);

            this.mNavController.pushFragment(aux);

        }else if(viewId == R.id.buttonFrag){

            Toast.makeText(getBaseContext(), "Sign Out", Toast.LENGTH_LONG).show();
            FirebaseAuth.getInstance().signOut();


            this.finish();

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        }else if(viewId == R.id.classlink){

            Toast.makeText(getBaseContext(), "NEW CLASS", Toast.LENGTH_LONG).show();
            OriginalFragment aux = new ClassFragment();
            OriginalFragment current = (OriginalFragment) mNavController.getCurrentFrag();
            aux.setTAG(current.getTAG());
            this.mNavController.pushFragment(aux);

        }else if(viewId == R.id.followUnfollow){

            Toast.makeText(getBaseContext(), "dejar de seguir", Toast.LENGTH_LONG).show();


        }else if(viewId == R.id.planlink ){

            OriginalFragment aux = new PlanFragment();
            OriginalFragment current = (OriginalFragment) mNavController.getCurrentFrag();
            aux.setTAG(current.getTAG());
            this.mNavController.pushFragment(aux);

        }


    }

    @Override
    public String StringRespond(String target) {
        return null;
    }


    public User getUser(){

        return this.currentUser;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        onBackPressed();
        System.out.println("SETTINGUPDOWNHERE!!!!!!!!!!!!!!!!!!");
        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_EDIT) {
            if (resultCode == RESULT_OK) {

                User user = (User) data.getSerializableExtra("user");

                Uri uri = data.getData();

                ProfileFragment aux = (ProfileFragment) mNavController.getCurrentFrag();

                if (aux != null && uri != null) {
                    aux.changePic(uri);
                }


            }else if(resultCode == RESULT_CANCELED){

                Toast.makeText(MainActivity.this, R.string.Cancel,
                        Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void tabReselected(int tabId){

        mNavController.clearStack();

        if(getSupportActionBar()!=null){

            if(bottomBar.getCurrentTabId() == R.id.Home ){

                getSupportActionBar().setTitle(R.string.app_name);

            }else if(bottomBar.getCurrentTabId() == R.id.Search){

                getSupportActionBar().setTitle(R.string.Search);

            }else{

                getSupportActionBar().setTitle(R.string.Profile);

                ProfileFragment frag = (ProfileFragment) mNavController.getCurrentFrag();

                if (frag != null) {

                    frag.update(currentUser);
                    frag.progressDismiss();

                }

            }

        }

    }

    public void tabSelected(int tabId){

       if(getSupportActionBar() != null) {

           if (tabId == R.id.Home) {

               flagTab = false;
               mNavController.switchTab(INDEX_HOME);

               if (mNavController.isRootFragment()) {

                   getSupportActionBar().setTitle(R.string.app_name);

               } else {

                   OriginalFragment aux = (OriginalFragment) mNavController.getCurrentFrag();
                   getSupportActionBar().setTitle(aux.getTAG());

               }

           } else if (tabId == R.id.Profile) {


               flagTab = true;
               mNavController.switchTab(INDEX_PROFILE);

               if (mNavController.isRootFragment()) {

                   getSupportActionBar().setTitle(R.string.Profile);
                   ProfileFragment frag = (ProfileFragment) mNavController.getCurrentFrag();

                   if (frag != null) {

                       frag.update(currentUser);
                       frag.progressDismiss();

                   }


               } else {

                   OriginalFragment aux = (OriginalFragment) mNavController.getCurrentFrag();
                   getSupportActionBar().setTitle(aux.getTAG());

               }

           } else if (tabId == R.id.Search) {

               flagTab = false;
               mNavController.switchTab(INDEX_SEARCH);

               if (mNavController.isRootFragment()) {

                   getSupportActionBar().setTitle(R.string.Search);

               } else {

                   OriginalFragment aux = (OriginalFragment) mNavController.getCurrentFrag();
                   getSupportActionBar().setTitle(aux.getTAG());

               }

           }
       }

    }

    public void refreshView(){


        if(flagTab && mNavController.isRootFragment()){

            System.out.println("Refreshing");

            ProfileFragment frag = (ProfileFragment) mNavController.getCurrentFrag();

            if (frag != null) {

                frag.update(currentUser);
                frag.progressDismiss();

            }

        }

    }


}
