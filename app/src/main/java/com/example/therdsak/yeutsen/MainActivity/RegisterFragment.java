package com.example.therdsak.yeutsen.mainactivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.therdsak.yeutsen.pageractivity.CheckTime;
import com.example.therdsak.yeutsen.pageractivity.PagerActivity;
import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.service.YeutSenService;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;
import com.github.channguyen.rsv.RangeSliderView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Therdsak on 9/28/2016.
 */
public class RegisterFragment extends Fragment {
    private static final String EXTRA_TIME = "TimeDialogFragment";
    private static final String DIALOG_TIME = "MainFragment";
    private static final String TAG = "MainFragment";
    private static final int FIRST_BTN = 1;
    private static final int SECOND_BTN = 2;
    private int lengthOfAlert;
    private CheckTime mCheckTime;
    private String checkDayOfWeek[] = new String[7];
    private String sTimeIn;
    private String sTimeOut;

    private Button buttonTimeIn;
    private Button buttonTimeOut;
    private Button buttonEnter;
    private Calendar calTimeIn, calTimeOut;

    ImageView Sunday;
    ImageView Monday;
    ImageView Tuesday;
    ImageView Wednesday;
    ImageView Thursday;
    ImageView Friday;
    ImageView Saturday;
    RangeSliderView TimeSeekBar;

    boolean flag[] = new boolean[7];

    Date startTimeDate, endTimeDate;



    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void checkDayOfWeek() {


        if (checkDayOfWeek[0].equals("false")
                && checkDayOfWeek[1].equals("false")
                && checkDayOfWeek[2].equals("false")
                && checkDayOfWeek[3].equals("false")
                && checkDayOfWeek[4].equals("false")
                && checkDayOfWeek[5].equals("false")
                && checkDayOfWeek[6].equals("false")) {
            buttonEnter.setEnabled(false);
            buttonEnter.setBackgroundColor(getResources().getColor(R.color.grey));
        } else {
            buttonEnter.setEnabled(true);
            buttonEnter.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register, container, false);
        Sunday = (ImageView) view.findViewById(R.id.sunday);
        Monday = (ImageView) view.findViewById(R.id.monday);
        Tuesday = (ImageView) view.findViewById(R.id.tuesday);
        Wednesday = (ImageView) view.findViewById(R.id.wednesday);
        Thursday = (ImageView) view.findViewById(R.id.thursday);
        Friday = (ImageView) view.findViewById(R.id.friday);
        Saturday = (ImageView) view.findViewById(R.id.saturday);


        mCheckTime = CheckTime.newInstance(getActivity());

        if (YeutSenPreference.getDayOfWeek(getActivity()) == null) {
            for (int i = 1; i <= 5; i++) {
                checkDayOfWeek[i] = "true";
                flag[i] = true;
            }
            checkDayOfWeek[0] = "false";
            checkDayOfWeek[6] = "false";
            flag[0] = false;
            flag[6] = false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Get Window
            final Window window = getActivity().getWindow();
            // Set Fullscreen
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_FULLSCREEN
            );

            window.setStatusBarColor(Color.TRANSPARENT);
        }


        Sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag[0]) {
                    Sunday.setImageResource(R.drawable.color_sunday);
                    flag[0] = true;
                    checkDayOfWeek[0] = "true";
                    Log.d(TAG, "onClick: Sun " + checkDayOfWeek[0]);
                } else {
                    Sunday.setImageResource(R.drawable.white_sunday);
                    flag[0] = false;
                    checkDayOfWeek[0] = "false";
                    Log.d(TAG, "onClick: Sun " + checkDayOfWeek[0]);
                }
                checkDayOfWeek();
            }
        });


        Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag[1]) {
                    Monday.setImageResource(R.drawable.color_monday);
                    flag[1] = true;
                    checkDayOfWeek[1] = "true";
                    Log.d(TAG, "onClick: Mon " + checkDayOfWeek[1]);
                } else {
                    Monday.setImageResource(R.drawable.white_monday);
                    flag[1] = false;
                    checkDayOfWeek[1] = "false";
                    Log.d(TAG, "onClick: Mon " + checkDayOfWeek[1]);
                }
                checkDayOfWeek();
            }
        });


        Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag[2]) {
                    Tuesday.setImageResource(R.drawable.color_tuesday);
                    flag[2] = true;
                    checkDayOfWeek[2] = "true";
                    Log.d(TAG, "onClick: Tues " + checkDayOfWeek[2]);
                } else {
                    Tuesday.setImageResource(R.drawable.white_tuesday);
                    flag[2] = false;
                    checkDayOfWeek[2] = "false";
                    Log.d(TAG, "onClick: Tues " + checkDayOfWeek[2]);
                }
                checkDayOfWeek();
            }
        });


        Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag[3]) {
                    Wednesday.setImageResource(R.drawable.color_wednesday);
                    flag[3] = true;
                    checkDayOfWeek[3] = "true";
                    Log.d(TAG, "onClick: Wed " + checkDayOfWeek[3]);
                } else {
                    Wednesday.setImageResource(R.drawable.white_wednesday);
                    flag[3] = false;
                    checkDayOfWeek[3] = "false";
                    Log.d(TAG, "onClick: Wed " + checkDayOfWeek[3]);
                }
                checkDayOfWeek();
            }
        });


        Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag[4]) {
                    Thursday.setImageResource(R.drawable.color_thursday);
                    flag[4] = true;
                    checkDayOfWeek[4] = "true";
                    Log.d(TAG, "onClick: Thur " + checkDayOfWeek[4]);
                } else {
                    Thursday.setImageResource(R.drawable.white_thursday);
                    flag[4] = false;
                    checkDayOfWeek[4] = "false";
                    Log.d(TAG, "onClick: Thur " + checkDayOfWeek[4]);
                }
                checkDayOfWeek();
            }
        });


        Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag[5]) {
                    Friday.setImageResource(R.drawable.color_friday);
                    flag[5] = true;
                    checkDayOfWeek[5] = "true";
                    Log.d(TAG, "onClick: Fri " + checkDayOfWeek[5]);
                } else {
                    Friday.setImageResource(R.drawable.white_friday);
                    flag[5] = false;
                    checkDayOfWeek[5] = "false";
                    Log.d(TAG, "onClick: Fri " + checkDayOfWeek[5]);
                }
                checkDayOfWeek();
            }
        });


        Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag[6]) {
                    Saturday.setImageResource(R.drawable.color_saturday);
                    flag[6] = true;
                    checkDayOfWeek[6] = "true";
                    Log.d(TAG, "onClick: Sat " + checkDayOfWeek[6]);
                } else {
                    Saturday.setImageResource(R.drawable.white_saturday);
                    flag[6] = false;
                    checkDayOfWeek[6] = "false";
                    Log.d(TAG, "onClick: Sat " + checkDayOfWeek[6]);
                }
                checkDayOfWeek();
            }
        });

//
//        checkBox_week = (CheckBox) view.findViewById(R.id.check_week);
//        checkBox_week.setChecked(isChecked);
//        checkBox_week.setEnabled(false);
//        checkBox_week.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if (isChecked == true) {
//                    Monday.setImageResource(R.drawable.color_monday);
//                    Tuesday.setImageResource(R.drawable.color_tuesday);
//                    Wednesday.setImageResource(R.drawable.color_wednesday);
//                    Thursday.setImageResource(R.drawable.color_thursday);
//                    Friday.setImageResource(R.drawable.color_friday);
//
//                } else {
//
//                }
//            }
//        });

        calTimeIn = Calendar.getInstance();
        calTimeIn.set(calTimeIn.get(Calendar.YEAR), calTimeIn.get(Calendar.MONTH), calTimeIn.get(Calendar.DATE), 8, 00, 00);
        startTimeDate = calTimeIn.getTime();

        calTimeOut = Calendar.getInstance();
        calTimeOut.set(calTimeOut.get(Calendar.YEAR), calTimeOut.get(Calendar.MONTH), calTimeOut.get(Calendar.DATE), 17, 00, 00);
        endTimeDate = calTimeOut.getTime();


        buttonTimeIn = (Button) view.findViewById(R.id.button_time_in);
        sTimeIn = (YeutSenPreference.getDateTimeIn(getActivity()) == 0) ? getFormattedTime(startTimeDate) : getFormattedTime(new Date(YeutSenPreference.getDateTimeIn(getActivity())));
        buttonTimeIn.setText(sTimeIn);
        buttonTimeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                TimeDialogFragment dialogFragment = TimeDialogFragment.newInstance(startTimeDate);
                dialogFragment.setTargetFragment(RegisterFragment.this, FIRST_BTN);
                dialogFragment.show(fm, DIALOG_TIME);
                Log.d(TAG, "Time 1: ");
            }
        });

        buttonTimeOut = (Button) view.findViewById(R.id.button_time_out);
        sTimeOut = (YeutSenPreference.getDateTimeOut(getActivity()) == 0) ? getFormattedTime(endTimeDate) : getFormattedTime(new Date(YeutSenPreference.getDateTimeOut(getActivity())));
        buttonTimeOut.setText(sTimeOut);
        buttonTimeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                TimeDialogFragment dialogFragment = TimeDialogFragment.newInstance(endTimeDate);
                dialogFragment.setTargetFragment(RegisterFragment.this, SECOND_BTN);
                dialogFragment.show(fm, DIALOG_TIME);
                Log.d(TAG, "Time 2: ");
            }
        });


        buttonEnter = (Button) view.findViewById(R.id.enter_working);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");


                StringBuffer result = new StringBuffer();
                result.append(checkDayOfWeek[0]);
                result.append(",").append(checkDayOfWeek[1]);
                result.append(",").append(checkDayOfWeek[2]);
                result.append(",").append(checkDayOfWeek[3]);
                result.append(",").append(checkDayOfWeek[4]);
                result.append(",").append(checkDayOfWeek[5]);
                result.append(",").append(checkDayOfWeek[6]);

                Log.d(TAG, "onClick: Result " + result);


//                Set Value in SharedPref.
                YeutSenPreference.setButtonSave(getActivity(),true);
                YeutSenPreference.setDayOfWeek(getActivity(), result.toString());
                YeutSenPreference.setDateTimeIn(getActivity(), startTimeDate.getTime());
                YeutSenPreference.setDateTimeOut(getActivity(), endTimeDate.getTime());
                YeutSenPreference.setLengthTimeAlert(getActivity(), lengthOfAlert);


                Log.d(TAG, "onClick: setDateTimeIn " + new Date(YeutSenPreference.getDateTimeIn(getActivity())));
                Log.d(TAG, "onClick: setDateTimeOut " + new Date(YeutSenPreference.getDateTimeOut(getActivity())));
                Log.d(TAG, "onClick: setLength " + YeutSenPreference.getLengthTimeAlert(getActivity()));

                mCheckTime.setDayOfWeek();
                if (!mCheckTime.isDayOfWeekFunction(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))) {
                    mCheckTime.setTimeToAlertNextDay(); //set another day
                } else {
                    YeutSenService.setServiceAlarm(getActivity(), 1);
                }


                new AsyncTask<Integer, Long, Boolean>() {
                    ProgressDialog pd = new ProgressDialog(getActivity());
                    Intent myIntent = new Intent(getActivity(), PagerActivity.class);

                    @Override
                    protected Boolean doInBackground(Integer... params) {
                        pd.setTitle("Loading Activity");
                        pd.setMessage("Please Wait ...");
                        pd.setMax(params[0]);
                        pd.setIndeterminate(false);
                        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                        publishProgress(0L);

                        long start = System.currentTimeMillis();
                        long waitTime = params[0] * 1000;
                        try {
                            while (System.currentTimeMillis() - start < waitTime) {
                                Thread.sleep(200);
                                publishProgress(System.currentTimeMillis() - start);
                            }
                        } catch (Exception e) {
                            return false;
                        }

                        return true;
                    }

                    @Override
                    protected void onProgressUpdate(Long... values) {
                        if (values[0] == 0) {
                            pd.show();
                        } else {
                            pd.setProgress((int) (values[0] / 1000));
                        }
                    }

                    @Override
                    protected void onPostExecute(Boolean result) {
                        pd.dismiss();
                        startActivity(myIntent);
                        getActivity().finish();
                    }
                }.execute(2);

            }
        });

        TimeSeekBar = (RangeSliderView) view.findViewById(R.id.SeekBar_time_alert);
        TimeSeekBar.setRangeCount(3);
//                        //      TimeSeekBar.setFilledColor(R.color.primary_dark);
//                        //      TimeSeekBar.setEmptyColor(R.color.colorAccent);
//                    TimeSeekBar.setMinimumHeight(5);
        TimeSeekBar.setBarHeightPercent(0.1f);
        TimeSeekBar.setSlotRadiusPercent(0.1f);
        TimeSeekBar.setSliderRadiusPercent(0.1f);
        TimeSeekBar.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                Log.d(TAG, "onClick: ");
                TimeSeekBar.setTag(index);
                switch (index) {
                    case 0:
                        lengthOfAlert = 30;
                        break;
                    case 1:
                        lengthOfAlert = 45;
                        break;
                    case 2:
                        lengthOfAlert = 60;
                        break;
                }
                Log.d(TAG, "onSlide: length " + lengthOfAlert);
                Log.d(TAG, "onSlide1: " + index);
//                            }
//                               };
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (YeutSenPreference.getDayOfWeek(getActivity()) != null) {
            for (int i = 0; i <= 6; i++) {
                if (mCheckTime.isDayOfWeekFunction(i + 1)) {
                    switch (i) {
                        case 0:
                            Sunday.setImageResource(R.drawable.color_sunday);
                            break;
                        case 1:
                            Monday.setImageResource(R.drawable.color_monday);
                            break;
                        case 2:
                            Tuesday.setImageResource(R.drawable.color_tuesday);
                            break;
                        case 3:
                            Wednesday.setImageResource(R.drawable.color_wednesday);
                            break;
                        case 4:
                            Thursday.setImageResource(R.drawable.color_thursday);
                            break;
                        case 5:
                            Friday.setImageResource(R.drawable.color_friday);
                            break;
                        case 6:
                            Saturday.setImageResource(R.drawable.color_saturday);
                            break;
                    }
                    flag[i] = true;
                } else {
                    switch (i) {
                        case 0:
                            Sunday.setImageResource(R.drawable.white_sunday);
                            break;
                        case 1:
                            Monday.setImageResource(R.drawable.white_monday);
                            break;
                        case 2:
                            Tuesday.setImageResource(R.drawable.white_tuesday);
                            break;
                        case 3:
                            Wednesday.setImageResource(R.drawable.white_wednesday);
                            break;
                        case 4:
                            Thursday.setImageResource(R.drawable.white_thursday);
                            break;
                        case 5:
                            Friday.setImageResource(R.drawable.white_friday);
                            break;
                        case 6:
                            Saturday.setImageResource(R.drawable.white_saturday);
                            break;
                    }
                    flag[i] = false;
                }
            }
        }else{
            Monday.setImageResource(R.drawable.color_monday);
            Tuesday.setImageResource(R.drawable.color_tuesday);
            Wednesday.setImageResource(R.drawable.color_wednesday);
            Thursday.setImageResource(R.drawable.color_thursday);
            Friday.setImageResource(R.drawable.color_friday);
        }

        if (YeutSenPreference.getLengthTimeAlert(getActivity()) == 0) {
            lengthOfAlert = 30;
        } else {
            Log.d(TAG, "onViewCreated: " + YeutSenPreference.getLengthTimeAlert(getActivity()));
            switch (YeutSenPreference.getLengthTimeAlert(getActivity())) {
                case 30:
                    Log.d(TAG, "onViewCreated: " + 0);
                    TimeSeekBar.setTag(0);
                    TimeSeekBar.setInitialIndex(0);
                    TimeSeekBar.setBottom(0);
                    break;
                case 45:
                    Log.d(TAG, "onViewCreated: " + 1);
                    TimeSeekBar.setTag(1);
                    TimeSeekBar.setInitialIndex(1);
                    TimeSeekBar.setBottom(1);
                    break;
                case 60:
                    Log.d(TAG, "onViewCreated: " + 2);
                    TimeSeekBar.setTag(2);
                    TimeSeekBar.setInitialIndex(2);
                    TimeSeekBar.setBottom(2);
                    break;
            }
        }

        Log.d(TAG, "onCreate: " + checkDayOfWeek);


    }

    private String getFormattedTime(Date date) {
        return new SimpleDateFormat("hh:mm a").format(date);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {
                case FIRST_BTN:
                    startTimeDate = (Date) data.getSerializableExtra(EXTRA_TIME);
                    Log.d(TAG, "onActivityResult: Cal1 " + startTimeDate);
                    buttonTimeIn.setText(getFormattedTime(startTimeDate));
                    break;
                case SECOND_BTN:
                    endTimeDate = (Date) data.getSerializableExtra(EXTRA_TIME);
                    Log.d(TAG, "onActivityResult: Cal2 " + endTimeDate);
                    buttonTimeOut.setText(getFormattedTime(endTimeDate));
                    break;
            }
        }
    }


}

