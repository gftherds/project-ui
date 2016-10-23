package com.example.therdsak.yeutsen.pageractivity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.therdsak.yeutsen.mainactivity.firstset.WelcomeFragment;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class PagerActivity extends SingleFragmentActivity {
    private static final String TAG = "PagerActivity";
    @Override
    protected Fragment onCreateFragment() {
        Log.d(TAG, "onCreateFragment: ");
//        return WelcomeFragment.newInstance();
        return PagerFragment.newInstance();
    }

    public static Intent newIntent(Context context){
        return new Intent(context,PagerActivity.class);
    }

}



