package com.example.therdsak.yeutsen.MainActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.therdsak.yeutsen.R;

/**
 * Created by Therdsak on 9/28/2016.
 */
public class SplashFragment extends Fragment {

//    private static long SPLASH_TIME_OUT = 3000;
//    Handler handler;
//    Runnable runnable;
 //   long delay_time;

    public static SplashFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Get Window
            final Window window = getActivity().getWindow();
            // Set Fullscreen
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_FULLSCREEN
            );
            // Set status bar color
            // ! Can also be set in style resource (/res/values-v21/styles.xml)
            // <item name="android:statusBarColor">@android:color/transparent</item>
            window.setStatusBarColor(Color.TRANSPARENT);
        }

//        handler = new Handler();
//        runnable = new Runnable() {
//
//            @Override
//            public void run() {
//
//            }
//        };
//
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash_screen,container,false);


        return view;
    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        delay_time = SPLASH_TIME_OUT;
//        handler.postDelayed(runnable,delay_time);
//        SPLASH_TIME_OUT = System.currentTimeMillis();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        handler.removeCallbacks(runnable);
//        SPLASH_TIME_OUT = delay_time - (System.currentTimeMillis() - SPLASH_TIME_OUT);
//    }
}


