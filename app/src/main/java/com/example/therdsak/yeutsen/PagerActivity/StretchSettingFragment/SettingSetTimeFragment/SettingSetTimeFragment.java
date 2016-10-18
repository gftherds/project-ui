package com.example.therdsak.yeutsen.pageractivity.stretchsettingfragment.settingsettimefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.mainactivity.RegisterFragment;
import com.example.therdsak.yeutsen.pageractivity.CheckTime;
import com.example.therdsak.yeutsen.pageractivity.PagerFragment;
import com.example.therdsak.yeutsen.pageractivity.VisibleFragment;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Therdsak on 10/6/2016.
 */
public class SettingSetTimeFragment extends VisibleFragment {

    Button buttonSolve;
    Button buttonCancel;

    FragmentManager fm;
    Toolbar toolbarSetTime;
    TextView mTxtTimeDay;
    TextView mTxtLengthAlert;
    TextView mTxtDayOfWeek;
    private CheckTime mCheckTime;
    private boolean b[] = new boolean[7];
    private StringBuffer s = new StringBuffer();
    private String string;

    public static SettingSetTimeFragment newInstance() {

        Bundle args = new Bundle();

        SettingSetTimeFragment fragment = new SettingSetTimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_set_time, container, false);

        toolbarSetTime = (Toolbar) view.findViewById(R.id.toolbar_set_time);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarSetTime);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Set Time");
        mCheckTime = CheckTime.newInstance(getActivity());

        for (int i = 1; i <= 7; i++) {
            if (mCheckTime.isDayOfWeekFunction(i)) {
                switch (i) {
                    case 1:
                        string = "Sunday";
                        break;
                    case 2:
                        string = "Monday";
                        break;
                    case 3:
                        string = "Tuesday";
                        break;
                    case 4:
                        string = "Wednesday";
                        break;
                    case 5:
                        string = "Thursday";
                        break;
                    case 6:
                        string = "Friday";
                        break;
                    case 7:
                        string = "Saturday";
                        break;
                }
                s.append(string).append(" ");
            }
        }

        mTxtTimeDay = (TextView) view.findViewById(R.id.txt_time_day);
        mTxtDayOfWeek = (TextView) view.findViewById(R.id.txt_set_day);
        mTxtLengthAlert = (TextView) view.findViewById(R.id.txt_length_Alert);


        mTxtTimeDay.setText(getFormattedTime(new Date(YeutSenPreference.getDateTimeIn(getActivity()))) + " - " + getFormattedTime(new Date(YeutSenPreference.getDateTimeOut(getActivity()))));
        mTxtDayOfWeek.setText(s.toString());
        mTxtLengthAlert.setText(YeutSenPreference.getLengthTimeAlert(getActivity()).toString());


        fm = getActivity().getSupportFragmentManager();

        buttonSolve = (Button) view.findViewById(R.id.button_solve);
        buttonSolve.setOnClickListener(new View.OnClickListener() {
            public static final String TAG = "SettingSetTimeFragment";

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Fragment fragmentSolve = RegisterFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragment_container2, fragmentSolve).commit();
            }
        });

        buttonCancel = (Button) view.findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragmentCancel = PagerFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragment_container2, fragmentCancel).commit();


                Fragment f6 = new PagerFragment();
                fm.beginTransaction().replace(R.id.fragment_container2, f6).commit();
            }
        });


        return view;
    }

    private String getFormattedTime(Date date) {
        return new SimpleDateFormat("hh:mm a").format(date);
    }
}
