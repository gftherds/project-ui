package com.example.therdsak.yeutsen.PagerActivity;


import android.support.v4.app.Fragment;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class PagerActivity extends SingleFragmentActivity {


    @Override
    protected Fragment onCreateFragment() {
        return PagerFragment.newInstance();
    }
}



