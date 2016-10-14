package com.example.therdsak.yeutsen.pageractivity.showfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.service.YeutSenService;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;

import java.util.Calendar;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class ShowStretchingFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ShowStretchingFragment";
    private static final String textFinish = "Finish";
    private String output;
    private Thread thread;
    private int tempLengthOfTime;
    private int tempCount;
    private int seconds;
    private int hourEdit;
    private int minuteEdit;
    private int secondEdit;
    private Button btnFinished;
    private TextView mTextView;

    private Calendar calendar;
    private CallBack mCallBack;
    private boolean REQUEST_CODE_IN_APP;


    public interface CallBack {
        void getDialog();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBack = (CallBack) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

    public static ShowStretchingFragment newInstance() {
        Bundle args = new Bundle();
        ShowStretchingFragment fragment = new ShowStretchingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Animation anim;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        mTextView = (TextView) view.findViewById(R.id.txt_show_welcome);

        btnFinished = (Button) view.findViewById(R.id.show_stretching_button);
        btnFinished.setOnClickListener(this);
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.left_to_right);
        UpdateUI();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        mTextView.setAnimation(anim);
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        if (thread != null) {
            thread.interrupt();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_stretching_button:
                setColorBtn(false);
                Log.d(TAG, "onClick: ");
                callStartCount();
                break;
        }
    }

    private void setColorBtn(boolean isColorBtn){
        btnFinished.setEnabled(isColorBtn);
        int r ;
        r = (isColorBtn)? R.color.colorAccent : R.color.grey;
        btnFinished.setBackgroundColor(getResources().getColor(r));
    }

    public void callStartCount() {
        calendar = Calendar.getInstance();
//        tempLengthOfTime = YeutSenPreference.getLengthTimeAlert(getActivity());
        tempLengthOfTime = 1; // Temp Test
        calTimeFinish();
        YeutSenService.setServiceAlarm(getActivity());
        tempLengthOfTime = tempLengthOfTime * 60;
        callThreadCount();
    }

    public void calTimeFinish() {
        getTime(Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND);
        minuteEdit = minuteEdit + tempLengthOfTime;
        calendar.set(Calendar.HOUR, hourEdit);
        calendar.set(Calendar.MINUTE, minuteEdit);
        calendar.set(Calendar.SECOND, secondEdit);
        YeutSenPreference.setDateToAlert(getActivity(), calendar.getTime().getTime());
    }

    public void getTime(int hour, int minute, int second) {
        hourEdit = calendar.get(hour);
        minuteEdit = calendar.get(minute);
        secondEdit = calendar.get(second);
    }


    public void callThreadCount() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                   // send value to set UI
                    setTextBtnUI();
                    tempLengthOfTime--;
                    if (tempLengthOfTime == 0) {
                        mCallBack.getDialog();
                        break;
                    }
                    doFakeWork();
                }
            }
        });
        thread.start();
    }

    private void doFakeWork(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setTextBtnUI(){
        btnFinished.post(new Runnable() {
            @Override
            public void run() {
                String s;
                s = (tempLengthOfTime != 0) ? formatTime(tempLengthOfTime) : textFinish;
                if (s.equals(textFinish)) setColorBtn(true);
                btnFinished.setText(s);
            }
        });
    }


    public String formatTime(int millis) {
        output = "";
        seconds = millis;
        int minutes = seconds / 60;
        int hours = minutes / 60;

        seconds = seconds % 60;
        minutes = minutes % 60;
        hours = hours % 60;

        String secondsD = String.valueOf(seconds);
        String minutesD = String.valueOf(minutes);
        String hoursD = String.valueOf(hours);

        if (seconds < 10)
            secondsD = "0" + seconds;
        if (minutes < 10)
            minutesD = "0" + minutes;

        if (hours < 10)
            hoursD = "0" + hours;

        output = hoursD + " : " + minutesD + " : " + secondsD;
        return output;
    }

    public void UpdateUI() {

        if (Calendar.getInstance().getTime().getTime() < YeutSenPreference.getDateToAlert(getActivity())) {
            // Do setTime and callThread
            Log.d(TAG, "UpdateUI: ");
            setColorBtn(false);
            Log.d(TAG, "UpdateUI Calendar: " + Calendar.getInstance().getTime().getTime());
            Log.d(TAG, "UpdateUI Preference: " + YeutSenPreference.getDateToAlert(getActivity()));
            final Calendar cal1 = Calendar.getInstance();
            final Calendar cal2 = Calendar.getInstance();
            cal1.setTimeInMillis(YeutSenPreference.getDateToAlert(getActivity()));
            cal2.setTimeInMillis(Calendar.getInstance().getTime().getTime());
            Log.d(TAG, "UpdateUI: " + cal1.getTime().toString());
            Log.d(TAG, "UpdateUI cal 2 : " + cal2.getTime().toString());

            int hourCal1 = cal1.get(Calendar.HOUR) * 60 * 60;
            int minuteCal1 = cal1.get(Calendar.MINUTE) * 60;
            int secondCal1 = cal1.get(Calendar.SECOND);

            int hourCal2 = cal2.get(Calendar.HOUR) * 60 * 60;
            int minuteCal2 = cal2.get(Calendar.MINUTE) * 60;
            int secondCal2 = cal2.get(Calendar.SECOND);

            tempLengthOfTime = (hourCal1 + minuteCal1 + secondCal1) - (hourCal2 + minuteCal2 + secondCal2);
            if(thread == null) callThreadCount();
            Log.d(TAG, "UpdateUI: thread Alive" + thread.isAlive());
            Log.d(TAG, "UpdateUI The last: " + tempLengthOfTime);
        }

    }


}
