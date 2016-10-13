package com.example.therdsak.yeutsen.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;

import com.example.therdsak.yeutsen.Database.StretchLogDBSchama.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noppharat on 10/12/2016.
 */

public class StretchLogLab {
    private static StretchLogLab instance;

    public static StretchLogLab getInstance(Context context) {
        if(instance == null){
            instance = new StretchLogLab(context);
        }
        return instance;
    }

    public static ContentValues getContentValues(StretchLog stretchLog){
        ContentValues contentValues = new ContentValues();
        contentValues.put(StretchLogTable.Cols.ID, stretchLog.getId().toString());
        contentValues.put(StretchLogTable.Cols.USERID, stretchLog.getUserid().toString());
        contentValues.put(StretchLogTable.Cols.DATE, stretchLog.getDate().getTime());
        contentValues.put(StretchLogTable.Cols.STRETCHID, stretchLog.getStretchid().toString());
        return contentValues;
    }

    private Context context;
    private SQLiteDatabase database;

    private StretchLogLab(Context context){
        this.context = context.getApplicationContext();
    }

    public StretchLogCursorWrapper queryStretchLog(String whereCause, String[] whereArgs){
        Cursor cursor = database.query(StretchLogTable.NAME,
                null,
                whereCause,
                whereArgs,
                null,
                null,
                null);
        return new StretchLogCursorWrapper(cursor);
    }

    public List<StretchLog> getStretchLog(){
        List<StretchLog> stretchLogList = new ArrayList<>();
        StretchLogCursorWrapper cursorWrapper = queryStretchLog(null, null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                stretchLogList.add(cursorWrapper.getStretchLog());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return stretchLogList;
    }

    public void insertStretchLog(StretchLog stretchLog){
        ContentValues contentValues = getContentValues(stretchLog);
        database.insert(StretchLogTable.NAME, null, contentValues);
    }

}
