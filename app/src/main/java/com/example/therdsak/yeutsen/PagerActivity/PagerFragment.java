package com.example.therdsak.yeutsen.pageractivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.therdsak.yeutsen.mainactivity.TutorialFragment;
import com.example.therdsak.yeutsen.pageractivity.showfragment.ShowStretchingFragment;
import com.example.therdsak.yeutsen.pageractivity.stretchlistfragment.StretchListFragment;
import com.example.therdsak.yeutsen.pageractivity.stretchsettingfragment.settingaboutfragment.SettingAboutFragment;
import com.example.therdsak.yeutsen.pageractivity.stretchsettingfragment.settingsettimefragment.SettingSetTimeFragment;
import com.example.therdsak.yeutsen.pageractivity.summaryfragment.SummaryStretchingFragment;
import com.example.therdsak.yeutsen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Therdsak on 10/6/2016.
 */
public class PagerFragment extends Fragment {



   public static PagerFragment newInstance() {

        Bundle args = new Bundle();

        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private static final String TAG = "PagerActivity";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppBarLayout appBarLayout;
    FragmentManager fm;

    private int[] tabIcons = {

            //icon color black
            R.drawable.home_selected,
            R.drawable.list_selected,
            R.drawable.summary_selected
    };

    private int[] tabBlackIcons = {
            //icon color white
            R.drawable.home_unselected,
            R.drawable.list_unselected,
            R.drawable.summary_unselected
    };

//    @Override
//    public void onStart() {
//        super.onStart();
//        Log.d(TAG, "onStart: ");
//
//    }
//
//    @Override
//    public void onStop() {
//        Log.d(TAG, "onStop: ");
//        super.onStop();
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab_bar,container,false);

        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar_layout);

//        fm = getSupportFragmentManager();
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        setHasOptionsMenu(true);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabLayout.getTabAt(0).setIcon(R.drawable.home_selected);
                        tabLayout.getTabAt(1).setIcon(R.drawable.list_unselected);
                        tabLayout.getTabAt(2).setIcon(R.drawable.summary_unselected);
                        appBarLayout.setExpanded(true);
                        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Title");

                        break;
                    case 1:
                        tabLayout.getTabAt(0).setIcon(R.drawable.home_unselected);
                        tabLayout.getTabAt(1).setIcon(R.drawable.list_selected);
                        tabLayout.getTabAt(2).setIcon(R.drawable.summary_unselected);
                        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ListItem");
                        appBarLayout.setExpanded(true);
//                        getSupportActionBar().setTitle("ListItem");
                        break;
                    case 2:

                        tabLayout.getTabAt(0).setIcon(R.drawable.home_unselected);
                        tabLayout.getTabAt(1).setIcon(R.drawable.list_unselected);
                        tabLayout.getTabAt(2).setIcon(R.drawable.summary_selected);
                        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Summary");
                        appBarLayout.setExpanded(true);
//                        getSupportActionBar().setTitle("Summary");
                        break;
                }

            }



            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
      //  setRetainInstance(true);
        return view;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.home_selected);
        tabLayout.getTabAt(1).setIcon(R.drawable.list_unselected);
        tabLayout.getTabAt(2).setIcon(R.drawable.summary_unselected);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment( ShowStretchingFragment.newInstance(), "ONE");
//        adapter.addFragment(new ListStretchingFragment(), "TWO");
        adapter.addFragment( StretchListFragment.newInstance(), "TWO");
        adapter.addFragment( SummaryStretchingFragment.newInstance(), "THREE");
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
            return null;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
            inflater.inflate(R.menu.menu_main,menu);

            Log.d(TAG, "onCreateOptionsMenu: ");

        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_set_time :
                Fragment fragment = SettingSetTimeFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,fragment).commit();
                Log.d(TAG, " set time : " + R.id.mnu_set_time);
                return true;
            case R.id.mnu_tutorial :
                Fragment fragment1 = TutorialFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,fragment1).commit();
                Log.d(TAG, " tutorial : " + R.id.mnu_tutorial);
                return true;
            case R.id.mnu_notification :
                Fragment fragmentNotification = SettingNotificationFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,fragmentNotification).addToBackStack(null).commit();
                Log.d(TAG, " notification : " + R.id.mnu_notification);
                return true;
            case R.id.mnu_about :
                Fragment fragmentAbout = SettingAboutFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,fragmentAbout).addToBackStack(null).commit();
                Log.d(TAG, " about : " + R.id.mnu_about);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

