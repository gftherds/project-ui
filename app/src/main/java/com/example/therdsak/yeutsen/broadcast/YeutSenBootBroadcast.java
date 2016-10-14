package com.example.therdsak.yeutsen.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.therdsak.yeutsen.service.YeutSenService;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;

import java.util.Calendar;

/**
 * Created by Nutdanai on 10/12/2016.
 */

public class YeutSenBootBroadcast extends BroadcastReceiver {
    private static final String TAG = "YeutSenBootBroadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: YeutSenBootBroadcast");
        if(Calendar.getInstance().getTime().getTime() < YeutSenPreference.getDateToAlert(context)){
            YeutSenService.setServiceAlarm(context);
        }
    }
}
