package com.example.therdsak.yeutsen.pageractivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.pageractivity.dialogfragment.YeutSenDialogFragment;
import com.example.therdsak.yeutsen.pageractivity.showfragment.ShowStretchingFragment;

/**
 * Created by Therdsak on 10/6/2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity implements ShowStretchingFragment.CallBack {
    private static final String TAG = "SingleFragmentActivity";
    public boolean REQUEST_BOOLEAN_ACTIVITY = true;
    private Fragment fragment;
    private FragmentManager fm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_activity_fragment);


        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container5);

        if(fragment == null){
            fragment = onCreateFragment();

            fm.beginTransaction().add(R.id.fragment_container5,fragment).commit();

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        REQUEST_BOOLEAN_ACTIVITY = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        REQUEST_BOOLEAN_ACTIVITY = false;
    }

    @Override
    public void getDialog() {
        Log.d(TAG, "getDialog: ");
        if (REQUEST_BOOLEAN_ACTIVITY) {
            Log.d(TAG, "getDialog: isTrue");
            YeutSenDialogFragment yeutSenDialogFragment = new YeutSenDialogFragment();
            yeutSenDialogFragment.show(getSupportFragmentManager(), "test");
        }
    }


    protected abstract Fragment onCreateFragment();
}

