package com.example.therdsak.yeutsen.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Nutdanai on 10/10/2016.
 */

public class YeutSenPreference {
    private static final String PREF_TIME_LENGTH = "PREF_TIME_LENGTH";
    private static final String PREF_ALERT_NEXT_TIME = "PREF_ALERT_NEXT_TIME";
    private static final String PREF_DAY_OF_WEEK = "PREF_DAY_OF_WEEK";
    private static final String PREF_CHECK_TUTORAIL = "PREF_CHECK_TUTORAIL";
    private static final String PREF_START_TIME = "PREF_START_TIME";
    private static final String PREF_END_TIME = "PREF_END_TIME";
    private static final String PREF_BTN_ON_START = "PREF_BTN_ON_START";
    private static final String PREF_BTN_ON_STOP = "PREF_BTN_ON_STOP";

    public static SharedPreferences mySharedPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLengthTimeAlert(Context context, int timeLength) {
        mySharedPref(context).edit().putInt(PREF_TIME_LENGTH, timeLength).apply();
    }

    public static Integer getLengthTimeAlert(Context context) {
        return mySharedPref(context).getInt(PREF_TIME_LENGTH, 0);
    }

    public static void setDateToAlert(Context context, long nextTimeToAlert) {
        mySharedPref(context).edit().putLong(PREF_ALERT_NEXT_TIME, nextTimeToAlert).apply();
    }

    public static Long getDateToAlert(Context context) {
        return mySharedPref(context).getLong(PREF_ALERT_NEXT_TIME,0);
    }

    public static void setDateTimeIn(Context context, long startTime){
        mySharedPref(context).edit().putLong(PREF_START_TIME,startTime).apply();
    }
    public static Long getDateTimeIn(Context context){
        return mySharedPref(context).getLong(PREF_START_TIME,0);
    }

    public static void setDateTimeOut(Context context, long endTime){
        mySharedPref(context).edit().putLong(PREF_END_TIME,endTime).apply();
    }
    public static Long getDateTimeOut(Context context){
        return mySharedPref(context).getLong(PREF_END_TIME,0);
    }

    public static void setDayOfWeek(Context context,String dayOfWeek){
        mySharedPref(context).edit().putString(PREF_DAY_OF_WEEK,dayOfWeek).apply();
    }
    public static String getDayOfWeek(Context context){
        return mySharedPref(context).getString(PREF_DAY_OF_WEEK,null);
    }


    public static boolean isTutorailOn(Context context){
        return mySharedPref(context).getBoolean(PREF_CHECK_TUTORAIL,false);
    }

    public static void setTutorailOn(Context context,boolean b){
        mySharedPref(context).edit().putBoolean(PREF_CHECK_TUTORAIL,b).apply();
    }

    public static boolean isBtnOnStart(Context context){
        return mySharedPref(context).getBoolean(PREF_BTN_ON_START,false);
    }

    public static void setBtnOnStart(Context context,boolean b){
        mySharedPref(context).edit().putBoolean(PREF_BTN_ON_START,b).apply();
    }

    public static boolean isBtnOnStop(Context context){
        return mySharedPref(context).getBoolean(PREF_BTN_ON_STOP,false);
    }

    public static void setBtnOnStop(Context context,boolean b){
        mySharedPref(context).edit().putBoolean(PREF_BTN_ON_STOP,b).apply();
    }


}
