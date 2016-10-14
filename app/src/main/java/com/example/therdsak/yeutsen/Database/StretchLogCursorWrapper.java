package com.example.therdsak.yeutsen.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.therdsak.yeutsen.Database.StretchLogDBSchama.*;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Noppharat on 10/10/2016.
 */

public class StretchLogCursorWrapper extends CursorWrapper {

    public StretchLogCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public StretchLog getStretchLog(){
        String id = getString(getColumnIndex(StretchLogTable.Cols.ID));
        String userid = getString(getColumnIndex(StretchLogTable.Cols.USERID));
        long date = getLong(getColumnIndex(StretchLogTable.Cols.STRETCHDATE));
        int stretchId = getInt(getColumnIndex(StretchLogTable.Cols.STRETCHID));

        StretchLog stretchLog = new StretchLog(UUID.fromString(id));
        stretchLog.setUserid(userid);
        stretchLog.setDate(new Date(date));
        stretchLog.setStretchid(stretchId);

        return stretchLog;
    }
}
