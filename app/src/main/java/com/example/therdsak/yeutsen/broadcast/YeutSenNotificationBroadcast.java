package com.example.therdsak.yeutsen.broadcast;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.therdsak.yeutsen.service.YeutSenService;

/**
 * Created by Nutdanai on 10/12/2016.
 */

public class YeutSenNotificationBroadcast extends BroadcastReceiver {
    private static final String TAG = "NotificationBroadcast";

    public YeutSenNotificationBroadcast() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Notification calling");
        if (getResultCode() != Activity.RESULT_OK) {
            Log.d(TAG, "onReceive: Cancel");
            return;
        }
        Notification notification = intent.getParcelableExtra(YeutSenService.RECEIVER_SHOW_NOTIFICATION);

        //ไปเอา object notification มาจากตัว Intent
        NotificationManagerCompat.from(context).notify(0, notification);

        Log.i(TAG, "Notify new item displayed");
    }

}
