package com.example.therdsak.yeutsen.pageractivity.graphfragment;

import android.support.v4.app.Fragment;

import com.example.therdsak.yeutsen.pageractivity.SingleFragmentActivity;

/**
 * Created by Noppharat on 10/11/2016.
 */

public abstract class GraphActivity extends SingleFragmentActivity {
    @Override
    protected Fragment onCreateFragment() {
        return new GraphFragment();
    }
}
