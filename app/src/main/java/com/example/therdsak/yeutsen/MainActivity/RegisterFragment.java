package com.example.therdsak.yeutsen.mainactivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.therdsak.yeutsen.PagerActivity.PagerActivity;
import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.pageractivity.PagerActivity;
import com.example.therdsak.yeutsen.pageractivity.TimeCheck;
import com.example.therdsak.yeutsen.service.YeutSenService;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;
import com.github.channguyen.rsv.RangeSliderView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Therdsak on 9/28/2016.
 */
public class RegisterFragment extends Fragment {


    private boolean isChecked = true;

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
    private static final int THIRD_BTN = 3;
    private static final int FORTH_BTN = 4;

    TextView buttonFirstTime;
    TextView buttonSecondTime;
    TextView buttonThirdTime;
    TextView buttonFourTime;
    CheckBox  Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button buttonFirstTime;
    Button buttonSecondTime;
    Button buttonEnter;

    CheckBox checkBox_week;
    CheckBox checkBox_time_alert;


    ImageView Sunday;
    ImageView Monday;
    ImageView Tuesday;
    ImageView Wednesday;
    ImageView Thursday;
    ImageView Friday;
    ImageView Saturday;
    RangeSliderView TimeSeekBar;

    boolean flag = false;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
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





        Sunday = (ImageView) view.findViewById(R.id.sunday);
        Sunday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!flag) {
                    Sunday.setImageResource(R.drawable.color_sunday);
                    checkBox_week.setChecked(!isChecked);
                    checkBox_week.setEnabled(true);
                    flag = true;
//                    Monday.setImageResource(R.drawable.color_monday);
//                    Tuesday.setImageResource(R.drawable.color_tuesday);
//                    Wednesday.setImageResource(R.drawable.color_wednesday);
//                    Thursday.setImageResource(R.drawable.color_thursday);
//                    Friday.setImageResource(R.drawable.color_friday);
                } else {
                    Sunday.setImageResource(R.drawable.white_sunday);
                    flag = false;

                }
            }
        });

        Monday = (ImageView) view.findViewById(R.id.monday);
        Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag) {
                    Monday.setImageResource(R.drawable.color_monday);
                    checkBox_week.setChecked(!isChecked);
                    checkBox_week.setEnabled(true);
                    flag = true;
                } else {
                    Monday.setImageResource(R.drawable.white_monday);

                    flag = false;
                }
            }
        });

        Tuesday = (ImageView) view.findViewById(R.id.tuesday);
        Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag) {
                    Tuesday.setImageResource(R.drawable.color_tuesday);
                    checkBox_week.setChecked(!isChecked);
                    checkBox_week.setEnabled(true);
                    flag = true;
                } else {
                    Tuesday.setImageResource(R.drawable.white_tuesday);
                    flag = false;
                }
            }
        });

        Wednesday = (ImageView) view.findViewById(R.id.wednesday);
        Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag) {
                    Wednesday.setImageResource(R.drawable.color_wednesday);
                    checkBox_week.setChecked(!isChecked);
                    checkBox_week.setEnabled(true);
                    flag = true;
                } else {
                    Wednesday.setImageResource(R.drawable.white_wednesday);
                    flag = false;
                }
            }
        });

        Thursday = (ImageView) view.findViewById(R.id.thursday);
        Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag) {
                    Thursday.setImageResource(R.drawable.color_thursday);
                    checkBox_week.setChecked(!isChecked);
                    checkBox_week.setEnabled(true);
                    flag = true;
                } else {
                    Thursday.setImageResource(R.drawable.white_thursday);
                    flag = false;
                }
            }
        });

        Friday = (ImageView) view.findViewById(R.id.friday);
        Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag) {
                    Friday.setImageResource(R.drawable.color_friday);
                    checkBox_week.setChecked(!isChecked);
                    checkBox_week.setEnabled(true);
                    flag = true;
                } else {
                    Friday.setImageResource(R.drawable.white_friday);
                    flag = false;
                }
            }
        });

        Saturday = (ImageView) view.findViewById(R.id.saturday);
        Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag) {
                    Saturday.setImageResource(R.drawable.color_saturday);
                    checkBox_week.setChecked(!isChecked);
                    checkBox_week.setEnabled(true);
                    flag = true;
                } else {
                    Saturday.setImageResource(R.drawable.white_saturday);

                    flag = false;
                }
            }
        });


        checkBox_week = (CheckBox) view.findViewById(R.id.check_week);
        checkBox_week.setChecked(isChecked);
        checkBox_week.setEnabled(false);
        checkBox_week.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == true) {
                    Monday.setImageResource(R.drawable.color_monday);
                    Tuesday.setImageResource(R.drawable.color_tuesday);
                    Wednesday.setImageResource(R.drawable.color_wednesday);
                    Thursday.setImageResource(R.drawable.color_thursday);
                    Friday.setImageResource(R.drawable.color_friday);

                }else{
                }
            }
        });



        buttonFirstTime = (TextView) view.findViewById(R.id.button1);
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

        buttonSecondTime = (TextView) view.findViewById(R.id.button3);
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


        buttonThirdTime = (TextView) view.findViewById(R.id.button3);
        buttonThirdTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                TimeDialogFragment dialogFragment = TimeDialogFragment.newInstance(time.getTimeDate());
                dialogFragment.setTargetFragment(RegisterFragment.this, THIRD_BTN);
                dialogFragment.show(fm, DIALOG_TIME);
                Log.d(TAG, "Time 3: ");
            }
        });

//        buttonFourTime = (TextView) view.findViewById(R.id.button6);
//        buttonFourTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fm = getFragmentManager();
//                TimeDialogFragment dialogFragment = TimeDialogFragment.newInstance(time.getTimeDate());
//                dialogFragment.setTargetFragment(RegisterFragment.this, FORTH_BTN);
//                dialogFragment.show(fm, DIALOG_TIME);
//                Log.d(TAG, "Time 4: ");
//            }
//        });

        buttonEnter = (Button) view.findViewById(R.id.enter_working);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Intent i = new Intent(getActivity(), PagerActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

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



        TimeSeekBar = (RangeSliderView) view.findViewById(R.id.SeekBar_time_alert);
        TimeSeekBar.setRangeCount(3);
  //      TimeSeekBar.setFilledColor(R.color.primary_dark);
  //      TimeSeekBar.setEmptyColor(R.color.colorAccent);
//        TimeSeekBar.setMinimumHeight(5);
        TimeSeekBar.setBarHeightPercent(0.1f);
        TimeSeekBar.setSlotRadiusPercent(0.1f);
        TimeSeekBar.setSliderRadiusPercent(0.1f);
        TimeSeekBar.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                Log.d(TAG, "onClick: ");
                TimeSeekBar.setTag(index);
                Log.d(TAG, "onSlide1: " + index);
                    }
                }.execute(3);
            }
        });




//        checkBox_time_alert = (CheckBox) view.findViewById(R.id.checkbox_time_alert);
//        checkBox_time_alert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if(!flag){
//                    TimeSeekBar.setEnabled(true);
//                    flag = true;
//                }else {
//                    TimeSeekBar.setEnabled(false);
//                    flag = false;
//                }
//
//
//            }
//        });





        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Monday.setImageResource(R.drawable.color_monday);
        Tuesday.setImageResource(R.drawable.color_tuesday);
        Wednesday.setImageResource(R.drawable.color_wednesday);
        Thursday.setImageResource(R.drawable.color_thursday);
        Friday.setImageResource(R.drawable.color_friday);
        checkBox_week.setChecked(isChecked);
        checkBox_week.setEnabled(false);

    }

    private String getFormattedTime(Date date) {
        return new SimpleDateFormat("hh:mm a").format(date);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Date date = (Date) data.getSerializableExtra(TimeDialogFragment.EXTRA_TIME);

            time.setTimeDate(date);
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

