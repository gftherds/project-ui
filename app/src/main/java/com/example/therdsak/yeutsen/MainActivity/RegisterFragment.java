package com.example.therdsak.yeutsen.mainactivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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

import com.example.therdsak.yeutsen.pageractivity.PagerActivity;
import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.sharedpreference.YeutSenPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private static final String DIALOG_TIME = "MainFragment";
    private static final String TAG = "MainFragment";
    private static final int FIRST_BTN = 1;
    private static final int SECOND_BTN = 2;
    private static final int THIRD_BTN = 3;
    private static final int FORTH_BTN = 4;

    CheckBox  Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button buttonFirstTime;
    Button buttonSecondTime;
    Button buttonThirdTime;
    Button buttonFourTime;
    Button buttonEnter;

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
                TimeDialogFragment dialogFragment = TimeDialogFragment.newInstance(time.getTimeDate());
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
                TimeDialogFragment dialogFragment = TimeDialogFragment.newInstance(time.getTimeDate());
                dialogFragment.setTargetFragment(RegisterFragment.this, SECOND_BTN);
                dialogFragment.show(fm, DIALOG_TIME);
                Log.d(TAG, "Time 2: ");
            }
        });


        buttonThirdTime = (Button) view.findViewById(R.id.button3);
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

        buttonFourTime = (Button) view.findViewById(R.id.button4);
        buttonFourTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                TimeDialogFragment dialogFragment = TimeDialogFragment.newInstance(time.getTimeDate());
                dialogFragment.setTargetFragment(RegisterFragment.this, FORTH_BTN);
                dialogFragment.show(fm, DIALOG_TIME);
                Log.d(TAG, "Time 4: ");
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

                YeutSenPreference.setDayOfWeek(getActivity(),result.toString());

                int selected_id = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) getActivity().findViewById(selected_id);

                Intent i = new Intent(getActivity(), PagerActivity.class);
                startActivity(i);
                getActivity().finish();

            }
        });

        return view;
    }

    private String getFormattedTime(Date date) {
        return new SimpleDateFormat("h:mm a").format(date);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Date date = (Date) data.getSerializableExtra(TimeDialogFragment.EXTRA_TIME);

            time.setTimeDate(date);
            switch (requestCode) {
                case FIRST_BTN:
                    buttonFirstTime.setText(getFormattedTime(time.getTimeDate()));
                    break;
                case SECOND_BTN:
                    buttonSecondTime.setText(getFormattedTime(time.getTimeDate()));
                    break;
                case THIRD_BTN:
                    buttonThirdTime.setText(getFormattedTime(time.getTimeDate()));
                    break;
                case FORTH_BTN:
                    buttonFourTime.setText(getFormattedTime(time.getTimeDate()));
                    break;
            }
        }
    }


}

