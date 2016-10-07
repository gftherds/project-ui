package com.example.therdsak.yeutsen.PagerActivity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.therdsak.yeutsen.MainActivity.TutorialFragment;
import com.example.therdsak.yeutsen.PagerActivity.ListFragment.ListStretchingFragment;
import com.example.therdsak.yeutsen.PagerActivity.ShowFragment.ShowStretchingFragment;
import com.example.therdsak.yeutsen.PagerActivity.SummaryFragment.SummaryStretchingFragment;
import com.example.therdsak.yeutsen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class PagerActivity extends SingleFragmentActivity {


    @Override
    protected Fragment onCreateFragment() {
        return PagerFragment.newInstance();
    }
}



