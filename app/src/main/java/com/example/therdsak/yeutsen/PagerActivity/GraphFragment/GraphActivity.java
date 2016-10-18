package com.example.therdsak.yeutsen.PagerActivity.GraphFragment;

import android.support.v4.app.Fragment;

import com.example.therdsak.yeutsen.PagerActivity.SingleFragmentActivity;

/**
 * Created by Noppharat on 10/11/2016.
 */

public class GraphActivity extends SingleFragmentActivity {
    @Override
    protected Fragment onCreateFragment() {
        return new GraphFragment();
    }
}
