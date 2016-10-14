package com.example.therdsak.yeutsen.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.therdsak.yeutsen.pageractivity.dialogfragment.YeutSenDialogFragment;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;

import junit.framework.Test;

/**
 * Created by Nutdanai on 10/11/2016.
 */

public class TestOne extends Service {
    private static final String TAG = "TestOne";
    private static Context ctx;
    private boolean finish = false;
//    public TestOne(Context ctx) {
//    }

    public static Intent newIntent(Context context) {
        ctx = context;
        return new Intent(context,TestOne.class);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        Intent i = YeutSenDialogFragment.newIntent(ctx);//TestOne.newIntent(ctx);
        PendingIntent pi = PendingIntent.getService(ctx, 0, i, 0);
        AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, YeutSenPreference.getDateToAlert(ctx), pi);

    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
        super.onDestroy();

    }
}
