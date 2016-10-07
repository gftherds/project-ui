package com.example.therdsak.yeutsen.PagerActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.therdsak.yeutsen.R;

/**
 * Created by Therdsak on 10/6/2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_activity_fragment);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container5);

        if(fragment == null){
            fragment = onCreateFragment();

            fm.beginTransaction().add(R.id.fragment_container5,fragment).commit();


        }

    }

    protected abstract Fragment onCreateFragment();
}

