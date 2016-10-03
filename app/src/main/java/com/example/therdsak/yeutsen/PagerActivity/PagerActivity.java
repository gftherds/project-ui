package com.example.therdsak.yeutsen.PagerActivity;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.therdsak.yeutsen.PagerActivity.ListFragment.ListStretchingFragment;
import com.example.therdsak.yeutsen.PagerActivity.SettingFragment.SettingStretchingFragment;
import com.example.therdsak.yeutsen.PagerActivity.ShowFragment.ShowStretchingFragment;
import com.example.therdsak.yeutsen.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class PagerActivity extends AppCompatActivity {

    private static final String YOUR_CUSTOM_ACTION_BAR_TITLE = "title";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int[] tabIcons = {

            //icon color black
            R.drawable.home_color,
            R.drawable.list_color,
            R.drawable.setting_color
    };

    private int[] tabBlackIcons = {
            //icon color white
            R.drawable.home_white,
            R.drawable.list_white,
            R.drawable.setting_white
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintabbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //   getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//        setActionBarTitle();

//        getSupportActionBar().setTitle("Title");
//        getSupportActionBar().setTitle("ListItem");
//        getSupportActionBar().setTitle("Setting");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabLayout.getTabAt(0).setIcon(R.drawable.home_white);
                        tabLayout.getTabAt(1).setIcon(R.drawable.list_color);
                        tabLayout.getTabAt(2).setIcon(R.drawable.setting_color);
                        getSupportActionBar().setTitle("Title");
                        break;
                    case 1:
                        tabLayout.getTabAt(0).setIcon(R.drawable.home_color);
                        tabLayout.getTabAt(1).setIcon(R.drawable.list_white);
                        tabLayout.getTabAt(2).setIcon(R.drawable.setting_color);
                        getSupportActionBar().setTitle("ListItem");
                        break;
                    case 2:

                        tabLayout.getTabAt(0).setIcon(R.drawable.home_color);
                        tabLayout.getTabAt(1).setIcon(R.drawable.list_color);
                        tabLayout.getTabAt(2).setIcon(R.drawable.setting_white);
                        getSupportActionBar().setTitle("Setting");
                        break;
                }

            }



            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }



    private void setActionBarTitle(String title) {
//        YOUR_CUSTOM_ACTION_BAR_TITLE

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        if(id == R.id.action_setting){
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.home_white);
        tabLayout.getTabAt(1).setIcon(R.drawable.list_color);
        tabLayout.getTabAt(2).setIcon(R.drawable.setting_color);

//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabBlackIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabBlackIcons[2]);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ShowStretchingFragment(), "ONE");
        adapter.addFragment(new ListStretchingFragment(), "TWO");
        adapter.addFragment(new SettingStretchingFragment(), "THREE");
        viewPager.setAdapter(adapter);

    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return mFragmentTitleList.get(position);
            return null;
        }
    }




    }



