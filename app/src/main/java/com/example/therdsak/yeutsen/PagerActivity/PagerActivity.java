package com.example.therdsak.yeutsen.pageractivity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class PagerActivity extends SingleFragmentActivity {
    private static final String TAG = "PagerActivity";
    @Override
    protected Fragment onCreateFragment() {
        Log.d(TAG, "onCreateFragment: ");
        return PagerFragment.newInstance();
    }

    public static Intent newIntent(Context context){
        return new Intent(context,PagerActivity.class);
    }

    @Override
    public void getDialog() {

    }
}



