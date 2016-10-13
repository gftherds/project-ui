package com.example.therdsak.yeutsen.PagerActivity.StretchListFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.therdsak.yeutsen.PagerActivity.SingleFragmentActivity;

/**
 * Created by Noppharat on 10/10/2016.
 */

public class StretchInfoActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context, String sname, String spath){
        Intent intent = new Intent(context, StretchInfoActivity.class);
        intent.putExtra("sname", sname);
        intent.putExtra("spath", spath);
        return intent;
    }

    protected Fragment onCreateFragment() {
        String sname = (String) getIntent().getSerializableExtra("sname");
        String spath = (String) getIntent().getSerializableExtra("spath");
        Fragment fragment = StretchInfoFragment.newInstance(sname, spath);
        return fragment;
    }
}
