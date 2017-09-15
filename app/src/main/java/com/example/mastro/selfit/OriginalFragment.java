package com.example.mastro.selfit;

import android.support.v4.app.Fragment;

/**
 * Created by Mastro on 4/19/2017.
 */

public class OriginalFragment extends Fragment {

    String TAG = " ";
    boolean Flag = false;

    public void setTAG( String _tag){

        TAG = _tag;

    }

    public String getTAG(){

        return TAG;

    }

    public void setFlag(Boolean signal){

        Flag = signal;

    }

    public boolean getFlag(){

        return Flag;

    }

}
