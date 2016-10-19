package com.example.therdsak.yeutsen.mainactivity;

import android.content.Intent;
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
import com.example.therdsak.yeutsen.pageractivity.PagerActivity;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;

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
                if(!YeutSenPreference.isTutorailOn(MainActivity.this)) {
                    Fragment f1 = TutorialFragment.newInstance();
                    fm.beginTransaction().replace(R.id.fragment_container, f1).commit();
                    YeutSenPreference.setTutorailOn(MainActivity.this,true);
                }else{
                    if(!YeutSenPreference.isButtonSave(MainActivity.this)) {
                        Fragment f = RegisterFragment.newInstance();
                        fm.beginTransaction().replace(R.id.fragment_container, f).commit();
                    }else{
                        Intent myIntent = new Intent(MainActivity.this, PagerActivity.class);
                        startActivity(myIntent);
                    }
                }
            }
        }, 5000);

    }
}

