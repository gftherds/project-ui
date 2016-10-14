package com.example.therdsak.yeutsen.mainactivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.therdsak.yeutsen.R;

/**
 * Created by Therdsak on 9/28/2016.
 */
public class TutorialFragment extends Fragment {
    private static final String TAG = "TutorialFragment";

    public static TutorialFragment newInstance() {

        Bundle args = new Bundle();
        TutorialFragment fragment = new TutorialFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private MyViewPageAdapter myViewPagerAdapter;
    PrefManagerTutorial prefManager;
    ImagePageAdapter mAdapter;
    private int page = 0;
    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private Button btnSkip, btnStart;
    private int[] layouts;
    Handler handler;
    private int delay = 10000;
    FragmentManager fm;


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mAdapter.getCount() == page) {

            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getActivity().toString());
        fm = getActivity().getSupportFragmentManager();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutorial, container, false);

        prefManager = new PrefManagerTutorial(getActivity());

        if (!prefManager.isFirstTimeLaunch()) {

        }

        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

//        getActivity().setContentView(R.layout.tutorial);

        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) view.findViewById(R.id.layoutDots);
        btnSkip = (Button) view.findViewById(R.id.btn_skip);
        btnStart = (Button) view.findViewById(R.id.btn_start);

        btnSkip.setVisibility(View.INVISIBLE);

        layouts = new int[]{
                R.layout.slide1,
                R.layout.slide2,
                R.layout.slide3,
                R.layout.slide4
        };

        //adding bottom dots
        addBottomDots(0);

        //making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPageAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(1);
                if (current < layouts.length) {
                    viewPager.setCurrentItem(layouts.length - 1);
                } else {
                    launchHomeScreen();
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchHomeScreen();
            }
        });

        handler = new Handler();
        mAdapter = new ImagePageAdapter(getActivity().getSupportFragmentManager());

        return view;
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    /**
     * Making notification bar transparent
     */

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorActive[currentPage]);
    }

    public void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        Fragment f = RegisterFragment.newInstance();
        fm.beginTransaction()
                .replace(R.id.fragment_container, f)
                .commit();
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            page = position;
            addBottomDots(position);

            if (position == 0) {
                btnSkip.setVisibility(View.INVISIBLE);
                btnStart.setVisibility(View.VISIBLE);
            } else if (position == layouts.length - 1) {
                btnSkip.setVisibility(View.VISIBLE);
                btnSkip.setText(getString(R.string.begin));
            } else {
                btnSkip.setText(getString(R.string.skip));
                btnStart.setVisibility(View.INVISIBLE);
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private class ImagePageAdapter extends FragmentPagerAdapter {
        public ImagePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);

    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }


    /**
     * View pager adapter
     */

    private class MyViewPageAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPageAdapter() {


        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
