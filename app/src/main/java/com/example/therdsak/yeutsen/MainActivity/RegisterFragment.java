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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.pageractivity.PagerActivity;
import com.example.therdsak.yeutsen.pageractivity.TimeCheck;
import com.example.therdsak.yeutsen.service.YeutSenService;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Therdsak on 9/28/2016.
 */
public class RegisterFragment extends Fragment {


    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static final String EXTRA_TIME = "TimeDialogFragment";
    private static final String DIALOG_TIME = "MainFragment";
    private static final String TAG = "MainFragment";
    private static final int FIRST_BTN = 1;
    private static final int SECOND_BTN = 2;

    CheckBox  Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button buttonFirstTime;
    Button buttonSecondTime;
    Button buttonEnter;

    Calendar calStartTime, calEndTime;
    Date startTimeDate, endTimeDate;

    List<Boolean> listBooleanDay = new ArrayList<>();

    private TimeLab time = new TimeLab();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register, container, false);
//        calStartTime = Calendar.getInstance(); // Time1 set to Compare
//        calEndTime = Calendar.getInstance();  // Time2 set to Compare

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

        Monday = (CheckBox) view.findViewById(R.id.checkBox_monday);
        Tuesday = (CheckBox) view.findViewById(R.id.checkBox_tuesday);
        Wednesday = (CheckBox) view.findViewById(R.id.checkBox_wednesday);
        Thursday = (CheckBox) view.findViewById(R.id.checkBox_thursday);
        Friday = (CheckBox) view.findViewById(R.id.checkBox_friday);
        Saturday = (CheckBox) view.findViewById(R.id.checkBox_saturday);
        Sunday = (CheckBox) view.findViewById(R.id.checkBox_sunday);

        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);

        buttonFirstTime = (Button) view.findViewById(R.id.button1);
        buttonFirstTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                TimeDialogFragment dialogFragment = TimeDialogFragment.newInstance(startTimeDate);
                dialogFragment.setTargetFragment(RegisterFragment.this, FIRST_BTN);
                dialogFragment.show(fm, DIALOG_TIME);
                Log.d(TAG, "Time 1: ");
            }
        });

        buttonSecondTime = (Button) view.findViewById(R.id.button2);
        buttonSecondTime.setOnClickListener(new View.OnClickListener() {
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

                listBooleanDay.add(Sunday.isChecked());
                listBooleanDay.add(Monday.isChecked());
                listBooleanDay.add(Tuesday.isChecked());
                listBooleanDay.add(Wednesday.isChecked());
                listBooleanDay.add(Thursday.isChecked());
                listBooleanDay.add(Friday.isChecked());
                listBooleanDay.add(Saturday.isChecked());


                StringBuffer result = new StringBuffer();
                result.append(Sunday.isChecked());
                result.append(",").append(Monday.isChecked());
                result.append(",").append(Tuesday.isChecked());
                result.append(",").append(Wednesday.isChecked());
                result.append(",").append(Thursday.isChecked());
                result.append(",").append(Friday.isChecked());
                result.append(",").append(Saturday.isChecked());

                Log.d(TAG, "onClick: " +result);
                //Set Value in SharedPref.
                YeutSenPreference.setDayOfWeek(getActivity(),result.toString());
                YeutSenPreference.setDateTimeIn(getActivity(), startTimeDate.getTime());
                YeutSenPreference.setDateTimeOut(getActivity(), endTimeDate.getTime());

                Log.d(TAG, "onClick: Name Activity " + getActivity());
                Log.d(TAG, "onClick: setDateTimeIn" + new Date(YeutSenPreference.getDateTimeIn(getActivity())));

                TimeCheck.newInstance(getContext()).setDayOfWeek();
                if(!TimeCheck.newInstance(getActivity()).isDayOfWeekFunction(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))) {
                    TimeCheck.newInstance(getContext()).setTimeToAlertNextDay(); //set another day
                }else{
                    YeutSenService.setServiceAlarm(getActivity(),1);
                }


                int selected_id = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) getActivity().findViewById(selected_id);

                new AsyncTask<Integer, Long, Boolean>()
                {
                    ProgressDialog pd = new ProgressDialog(getActivity());
                    Intent myIntent = new Intent(getActivity(), PagerActivity.class);

                    @Override
                    protected Boolean doInBackground(Integer... params)
                    {
                        pd.setTitle("Loading Activity");
                        pd.setMessage("Please Wait ...");
                        pd.setMax(params[0]);
                        pd.setIndeterminate(false);
                        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                        publishProgress(0L);

                        long start = System.currentTimeMillis();
                        long waitTime = params[0] * 1000;
                        try
                        {
                            while (System.currentTimeMillis() - start < waitTime)
                            {
                                Thread.sleep(200);
                                publishProgress(System.currentTimeMillis() - start);
                            }
                        }
                        catch (Exception e)
                        {
                            return false;
                        }

                        return true;
                    }

                    @Override
                    protected void onProgressUpdate(Long... values)
                    {
                        if (values[0] == 0)
                        {
                            pd.show();
                        }
                        else
                        {
                            pd.setProgress((int) (values[0] / 1000));
                        }
                    }

                    @Override
                    protected void onPostExecute(Boolean result)
                    {
                        pd.dismiss();
                        startActivity(myIntent);
                        getActivity().finish();

                    }
                }.execute(3);
            }
        });
        return view;
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
                    Log.d(TAG, "onActivityResult: Cal1 " + startTimeDate ) ;
                    buttonFirstTime.setText(getFormattedTime(startTimeDate));
                    break;
                case SECOND_BTN:
                    endTimeDate = (Date) data.getSerializableExtra(EXTRA_TIME);
                    Log.d(TAG, "onActivityResult: Cal2 "+ endTimeDate );
                    buttonSecondTime.setText(getFormattedTime(endTimeDate));
                    break;
            }
        }
    }


}

