package com.example.therdsak.yeutsen.pageractivity.showfragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.therdsak.yeutsen.database.StretchLog;
import com.example.therdsak.yeutsen.database.StretchLogLab;
import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.pageractivity.CheckTime;
import com.example.therdsak.yeutsen.service.YeutSenService;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class ShowStretchingFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "ShowStretchingFragment";
    private static final String DIALOG_TIME = "ShowStretchListFragment";
    private static final int FIRST_BTN = 1;

    private TextView mTextView;
    private Button addButton;
    private Button btnFinished;
    private WebView randomStretch;

    private int tempLengthOfTime;
    private int randInt;
    private int seconds;
    private String output;
    private String assetPath = "file:///android_asset";
    private String stretchPhotoFolder = "stretch";
    private String jsonFileName = "stretch.json";
    private StretchLog stretchLog;

    private ArrayList<HashMap<String, String>> stretchList;
    private Thread thread;
    private CallBack mCallBack;
    private CheckTime mCheckTime;
    private Animation anim;
    private static final int REQUEST_CODE_TWO =2;
    private static final String textFinish ="Finish";


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
    }

    public static ShowStretchingFragment newInstance() {

        Bundle args = new Bundle();

        ShowStretchingFragment fragment = new ShowStretchingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void jsonStringToList(String jsonString){
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("stretch");
            HashMap<String, String> hashMapList;

            for(int i = 0 ; i < jsonArray.length() ; i++){
                JSONObject jsonObjectInside = jsonArray.getJSONObject(i);
                String stretchName = jsonObjectInside.getString("sname");
                String stretchPath = jsonObjectInside.getString("spath");

                hashMapList = new HashMap<>();
                hashMapList.put("sname", stretchName);
                hashMapList.put("spath", stretchPath);

                stretchList.add(hashMapList);
            }
        }catch(Exception e){

        }
    }


    private String jsonFileToString(String jsonFileName){
        String jsonString;
        try{
            InputStream inputStream = getActivity().getAssets().open(jsonFileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
            return jsonString;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stretchList = new ArrayList<>();
        jsonStringToList(jsonFileToString(jsonFileName));

        Random random = new Random();
        randInt = Math.abs(random.nextInt()) % 14;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container,false);
        mTextView = (TextView) view.findViewById(R.id.txt_show_welcome);

        randomStretch = (WebView) view.findViewById(R.id.random_stretch_gif);
        randomStretch.loadUrl(assetPath + File.separator + stretchPhotoFolder + File.separator + stretchList.get(randInt).get("spath"));
        randomStretch.setOnTouchListener(new View.OnTouchListener() {
            public final static int FINGER_RELEASED = 0;
            public final static int FINGER_TOUCHED = 1;
            public final static int FINGER_DRAGGING = 2;
            public final static int FINGER_UNDEFINED = 3;

            private int fingerState = FINGER_RELEASED;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if(fingerState == FINGER_RELEASED) fingerState = FINGER_TOUCHED;
                        else fingerState = FINGER_UNDEFINED;
                        Log.d(TAG, "onTouch: ACTION_DOWN " + fingerState);
                        break;
                    case MotionEvent.ACTION_UP:
//                        if(fingerState != FINGER_DRAGGING){
                            fingerState = FINGER_RELEASED;

                            FragmentManager fragmentManager = getFragmentManager();
                            DialogShowStretchingFragment dialogShowStretchingFragment = DialogShowStretchingFragment.newInstance(randInt);
                            dialogShowStretchingFragment.setTargetFragment(ShowStretchingFragment.this, FIRST_BTN);
                            dialogShowStretchingFragment.show(fragmentManager, DIALOG_TIME);
//                        }
//                        else if (fingerState == FINGER_DRAGGING) fingerState = FINGER_RELEASED;
//                        else fingerState = FINGER_UNDEFINED;
                        Log.d(TAG, "onTouch: ACTION_UP " + fingerState);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(fingerState == FINGER_TOUCHED || fingerState == FINGER_DRAGGING) fingerState = FINGER_DRAGGING;
                        else fingerState = FINGER_UNDEFINED;
                        Log.d(TAG, "onTouch: ACTION_MOVE " + fingerState );
                        break;
                    default:
                        fingerState = FINGER_UNDEFINED;

                }
                return false;
            }
        });

        btnFinished = (Button) view.findViewById(R.id.show_stretching_button);
        btnFinished.setOnClickListener(this);

        mCheckTime = CheckTime.newInstance(getActivity());
        mCheckTime.getLoopTime();

        setColorBtn( YeutSenPreference.isButton(getActivity()));
        anim = AnimationUtils.loadAnimation(getContext(), R.anim.left_to_right);
        UpdateUI();
        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == FIRST_BTN){
            int backposition = data.getIntExtra("position", 0);
            randomStretch.loadUrl(assetPath + File.separator + stretchPhotoFolder + File.separator + stretchList.get(backposition).get("spath"));
            randInt = backposition;
        }
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (thread != null) {
            thread.interrupt();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.show_stretching_button:
                setColorBtn(! YeutSenPreference.isButton(getActivity()));
                stretchLog = new StretchLog();
                stretchLog.setUserid("0");
                stretchLog.setStretchid(randInt);
                stretchLog.setDate(new Date());
                StretchLogLab.getInstance(getActivity()).insertStretchLog(stretchLog);
                Log.d(TAG, "onClick: ID : " + stretchLog.getId() + " USERID :  " + stretchLog.getUserid() + " DATE : " + stretchLog.getDate() + " STRETCHID : "  + stretchLog.getStretchid());
                Log.d(TAG, "onClick: ");
                callStartCount();

                break;
        }
    }

    public void setColorBtn(boolean isColorBtn) {
        btnFinished.setEnabled(isColorBtn);
        int r = (isColorBtn) ? R.color.colorAccent : R.color.grey;
        btnFinished.setBackgroundColor(getResources().getColor(r));
    }

    public void callStartCount() {
//        tempLengthOfTime = YeutSenPreference.getLengthTimeAlert(getActivity());
        tempLengthOfTime = 1; // Temp Test
        mCheckTime.calTimeFinish(tempLengthOfTime);
        YeutSenService.setServiceAlarm(getContext(), REQUEST_CODE_TWO);
        tempLengthOfTime = tempLengthOfTime * 60;
        callThreadCount();

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

    private void doFakeWork() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setTextBtnUI() {

        btnFinished.post(new Runnable() {
            @Override
            public void run() {
                String s;
                s = (tempLengthOfTime != 0) ? formatTime(tempLengthOfTime) : textFinish;
                if (s.equals(textFinish)) {
//                    if((Calendar.getInstance().getTime().getTime() + (YeutSenPreference.getLengthTimeAlert(getContext())*60000)) > YeutSenPreference.getDateTimeOut(getActivity())){
                    if((Calendar.getInstance().getTime().getTime() + 60000) > YeutSenPreference.getDateTimeOut(getActivity())){
                        setColorBtn(false);
                    }else{
                        setColorBtn(true);
                    }
                }
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
            if (Calendar.getInstance().getTime().getTime() < YeutSenPreference.getDateToAlert(getContext())) {
                // Do setTime and callThread
                Log.d(TAG, "UpdateUI: InTime ");
                setColorBtn(false);
                Log.d(TAG, "UpdateUI Calendar: " + Calendar.getInstance().getTime().getTime());
                Log.d(TAG, "UpdateUI Preference: " + YeutSenPreference.getDateToAlert(getContext()));
                final Calendar cal1 = Calendar.getInstance();
                final Calendar cal2 = Calendar.getInstance();
                cal1.setTimeInMillis(YeutSenPreference.getDateToAlert(getContext()));
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
                if (thread == null) callThreadCount();

                Log.d(TAG, "UpdateUI The last: " + tempLengthOfTime);
            }
    }


}
