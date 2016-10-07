package com.example.therdsak.yeutsen.MainActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
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


        Log.d(TAG, "onCreate: fragment manager: " + fm);
         Fragment f = SplashFragment.newInstance();
        fm.beginTransaction().add(R.id.fragment_container, f).commit();
        test();
    }
    private void test() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Second fragment after 5 seconds appears
                Fragment f1 = TutorialFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragment_container, f1).commit();
            }
        }, 5000);

    }
}

