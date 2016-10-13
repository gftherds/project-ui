package com.example.therdsak.yeutsen.PagerActivity.ShowFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.therdsak.yeutsen.Database.StretchLog;
import com.example.therdsak.yeutsen.Database.StretchLogLab;
import com.example.therdsak.yeutsen.R;

import java.util.Date;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class ShowStretchingFragment extends Fragment{
    
    private static final String TAG = "ShowStretchingFragment";

    private Button addButton;


    public static ShowStretchingFragment newInstance() {

        Bundle args = new Bundle();

        ShowStretchingFragment fragment = new ShowStretchingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container,false);

        addButton = (Button) view.findViewById(R.id.show_stretching_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StretchLog stretchLog = new StretchLog();
                stretchLog.setUserid("0");
                stretchLog.setStretchid(1);
                stretchLog.setDate(new Date());

                StretchLogLab.getInstance(getActivity()).insertStretchLog(stretchLog);
                Log.d(TAG, "onClick: ID : " + stretchLog.getId() + " USERID :  " + stretchLog.getUserid() + " DATE : " + stretchLog.getDate() + " STRETCHID : "  + stretchLog.getStretchid());
            }
        });

        return view;
    }
}
