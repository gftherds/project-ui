package com.example.therdsak.yeutsen.MainActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.therdsak.yeutsen.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FragmentManager fm;


    @LayoutRes
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        fm = getSupportFragmentManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Get Window
            final Window window = getWindow();
            // Set Fullscreen
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_FULLSCREEN
            );
            window.setStatusBarColor(Color.TRANSPARENT);
        }

//        fm = getSupportFragmentManager();
        Log.d(TAG, "onCreate: fragment manager: " + fm);

        Fragment f = SplashFragment.newInstance();
        fm.beginTransaction().add(R.id.fragment_container, f).commit();



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Fragment f1 = TutorialFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragment_container,f1).commit();
            }
        }, 5000);

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Second fragment after 5 seconds appears
//                Fragment f1 = TutorialFragment.newInstance();
//                gm.beginTransaction().add(R.id.fragment_container, f1).commit();
//            }
//        }, 5000);
//        test();
//

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        f = TutorialFragment.newInstance();
//        fm.beginTransaction().replace(R.id.fragment_container,f).commit();

//        f = RegisterFragment.newInstance();
//        fm.beginTransaction().replace(R.id.fragment_container,f).commit();

    }


//    @Override
//    public void sendSignal() {
//        Fragment f1 = RegisterFragment.newInstance();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f1).commit();
//    }
//
//    private void test() {
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Second fragment after 5 seconds appears
//                Fragment f1 = RegisterFragment.newInstance();
//                fm.beginTransaction().replace(R.id.fragment_container, f1).commit();
//            }
//        }, 5000);

    }
//}


