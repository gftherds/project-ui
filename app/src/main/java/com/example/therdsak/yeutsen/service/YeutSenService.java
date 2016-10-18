package com.example.therdsak.yeutsen.service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.therdsak.yeutsen.pageractivity.PagerActivity;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;


import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Nutdanai on 10/11/2016.
 */

public class YeutSenService extends IntentService {
    private static final String TAG = "YeutSenService";
    public static final String ACTION_SHOW_NOTIFICATION = "com.example.therdsak.yeutsen.ACTION_SHOW_NOTIFICATION";
    public static final String RECEIVER_SHOW_NOTIFICATION = "com.example.therdsak.yeutsen.RECEIVER_SHOW_NOTIFICATION";
    private static final String REQUEST_CODE = "request_code";
    public static final String REQUEST_STRING_CHECK = "REQUEST_STRING_CHECK";
    private static final int REQUEST_CODE_ONE = 1;
    private static final int REQUEST_CODE_TWO = 2;
    private static Intent intentSetService;
    static Context ctx;


    public YeutSenService() {
        super(TAG);
    }


    public static Intent newIntent(Context context) {
        ctx = context;
        return new Intent(context, YeutSenService.class);
    }

    //    TODO : (DO d d) setServiceAlarm
    public static void setServiceAlarm(Context context, int requestCode) {
        intentSetService = YeutSenService.newIntent(context);
        PendingIntent pi = PendingIntent.getService(context, 0, intentSetService, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        AlarmManager amTimeIn = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        switch (requestCode) {
            case REQUEST_CODE_ONE:
                Log.d(TAG, "setServiceAlarm: REQUEST_CODE_ONE " );
                YeutSenPreference.setBtnOnStart(context, false);
//                amTimeIn.setRepeating(AlarmManager.RTC_WAKEUP, YeutSenPreference.getDateTimeIn(context),60*1000, pi);
                amTimeIn.set(AlarmManager.RTC_WAKEUP, YeutSenPreference.getDateTimeIn(context), pi);
                Log.d(TAG, "setServiceAlarm: time1 " + new Date(YeutSenPreference.getDateTimeIn(context)));

                break;
            case REQUEST_CODE_TWO:
                Log.d(TAG, "setServiceAlarm: REQUEST_CODE_TWO ");
                am.set(AlarmManager.RTC_WAKEUP, YeutSenPreference.getDateToAlert(context), pi);
                Log.d(TAG, "setServiceAlarm: time2 " + new Date(YeutSenPreference.getDateToAlert(context)));
                break;
        }

    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        Intent i = PagerActivity.newIntent(this);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        builder.setTicker("Ticker!");
        builder.setContentTitle("Time Up !! ");
        builder.setContentText("Time = " + new SimpleDateFormat(" hh:mm:ss a").format(YeutSenPreference.getDateToAlert(this)));
        builder.setSmallIcon(android.R.drawable.ic_notification_overlay);
//        Ringtone ringtone = RingtoneManager.getRingtone(this,soundUri);
//        ringtone.play();
        builder.setSound(soundUri);
        builder.setContentIntent(pi);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        builder.setAutoCancel(true);


        Notification notification = builder.build();
//        notification.flags |= Notification.FLAG_INSISTENT;




        sendBackgroundNotification(0, notification);
        new Screen().on(YeutSenService.this);

    }


    private void sendBackgroundNotification(int requestCode, Notification notification) {
        Intent intent = new Intent(ACTION_SHOW_NOTIFICATION);
        intent.putExtra(REQUEST_CODE, requestCode);
        intent.putExtra(RECEIVER_SHOW_NOTIFICATION, notification);
        sendOrderedBroadcast(intent, RECEIVER_SHOW_NOTIFICATION, null, null, Activity.RESULT_OK, null, null);

    }
}
