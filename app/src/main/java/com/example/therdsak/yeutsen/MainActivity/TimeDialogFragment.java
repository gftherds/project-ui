package com.example.therdsak.yeutsen.mainactivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.example.therdsak.yeutsen.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class TimeDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String TAG = "TimeDialogFragment";
    public static final String EXTRA_TIME = "TimeDialogFragment";

    private Date date;
    TimePicker _TimePicker;

    public static TimeDialogFragment newInstance(Date date) {

        Bundle args = new Bundle();
        args.putSerializable("KEY", date);
        TimeDialogFragment fragment = new TimeDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        date = (Date) getArguments().getSerializable("KEY");
        Calendar calendar = Calendar.getInstance();

        if (date == null) {
            date = new Date();
        }
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);
        _TimePicker = (TimePicker) view.findViewById(R.id.time_dialog);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            _TimePicker.setHour(hour);
            _TimePicker.setMinute(minute);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle("Time");
        builder.setPositiveButton(android.R.string.ok, this);
        return builder.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hour = _TimePicker.getHour();
            int minute = _TimePicker.getMinute();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND,0);
            sendResult(Activity.RESULT_OK, calendar.getTime());
        }
    }

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
