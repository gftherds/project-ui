package com.example.therdsak.yeutsen.pageractivity;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.therdsak.yeutsen.service.YeutSenService;

/**
 * Created by Nutdanai on 10/12/2016.
 */

public class VisibleFragment extends Fragment {

    private static final String TAG = "VisibleFragment";

    public VisibleFragment() {}

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive");
            setResultCode(Activity.RESULT_CANCELED);
            Log.d(TAG, "onReceive: Finish");
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        IntentFilter filter = new IntentFilter(YeutSenService.ACTION_SHOW_NOTIFICATION);
        getActivity().registerReceiver(mBroadcastReceiver, filter, YeutSenService.RECEIVER_SHOW_NOTIFICATION, null);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }
}
