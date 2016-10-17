package com.example.therdsak.yeutsen.pageractivity.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.therdsak.yeutsen.mainactivity.MainActivity;
import com.example.therdsak.yeutsen.pageractivity.TimeCheck;
import com.example.therdsak.yeutsen.pageractivity.showfragment.ShowStretchingFragment;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;

import junit.framework.Test;

/**
 * Created by Nutdanai on 10/11/2016.
 */

public class YeutSenDialogFragment extends DialogFragment {
    private static final String TAG = "YeutSenDialogFragment";

    public static YeutSenDialogFragment newInstance() {
        Bundle args = new Bundle();

        YeutSenDialogFragment fragment = new YeutSenDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, YeutSenDialogFragment.class);
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: ");
        View v  = LayoutInflater.from(getActivity()).inflate(android.R.layout.select_dialog_item,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Time Up !!!");
        builder.setMessage("If you want to stop this Alert, you can press 'ok' ");
        builder.setView(v);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        return builder.create();

    }
}
