package com.example.therdsak.yeutsen.mainactivity.firstset;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.example.therdsak.yeutsen.R;

import static android.content.ContentValues.TAG;

/**
 * Created by outnotin on 10/23/2016 AD.
 */

public class SetTimeFragment extends Fragment {

    private Button startTimeButton;
    private Button finishTimeButton;
    private Button nextButton;
    private TimePicker startTimePicker;
    private TimePicker finishTimePicker;
    private Boolean isStartClick;
    private Boolean isFinishClick;

    private LinearLayout startTimeLinear;
    private LinearLayout finishTimeLinear;

    public static SetTimeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SetTimeFragment fragment = new SetTimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isStartClick = false;
        isFinishClick = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.set_time_fragment, container, false);

        startTimeLinear = (LinearLayout) v.findViewById(R.id.start_time_linear);
        finishTimeLinear = (LinearLayout) v.findViewById(R.id.finish_time_linear);

        startTimeButton = (Button) v.findViewById(R.id.start_time_label);

        startTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: start clicked" + startTimeLinear.getBottom());
                if(!isStartClick){
                    startTimeLinear.animate().setDuration(1000).translationY(startTimeButton.getBottom()/2).alpha(1.0f).start();
                    finishTimeButton.animate().setDuration(1000).translationY(startTimeLinear.getBottom()).start();
                    finishTimeLinear.animate().setDuration(1000).translationY(+1000).alpha(0.0f).start();
                    isStartClick = true;
                    isFinishClick = false;
                }else{
                    startTimeLinear.animate().setDuration(1000).translationY(-1000).alpha(0.0f).start();
                    finishTimeLinear.animate().setDuration(1000).translationY(+1000).alpha(0.0f).start();
                    finishTimeButton.animate().setDuration(1000).translationY(0).start();
                    isStartClick = false;
                }
            }
        });

        startTimePicker = (TimePicker) v.findViewById(R.id.start_time_picker);
        startTimePicker.setBackgroundColor(getResources().getColor(R.color.accent));

        finishTimeButton = (Button) v.findViewById(R.id.finish_time_label);
        finishTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFinishClick){
                    isFinishClick = true;
                    isStartClick = false;
                    finishTimeButton.animate().setDuration(1000).translationY(0).start();
                    finishTimeLinear.animate().setDuration(1000).translationY(finishTimeButton.getBottom() - 90).alpha(1.0f).start();
                    startTimeLinear.animate().setDuration(1000).translationY(-1000).alpha(0.0f).start();
                }else{
                    isFinishClick = false;
                    finishTimeButton.animate().setDuration(1000).translationY(0).start();
                    startTimeLinear.animate().setDuration(1000).translationY(-1000).alpha(0.0f).start();
                    finishTimeLinear.animate().setDuration(1000).translationY(+1000).alpha(0.0f).start();
                }
            }
        });
        finishTimePicker = (TimePicker) v.findViewById(R.id.finish_time_picker);
        finishTimePicker.setBackgroundColor(getResources().getColor(R.color.accent));

        nextButton = (Button) v.findViewById(R.id.time_next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDayAndLengthFragment fragment = SetDayAndLengthFragment.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container2 ,fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startTimeLinear.animate().alpha(0.0f).start();
        finishTimeLinear.animate().alpha(0.0f).start();

        finishTimeButton.setY(-startTimeLinear.getBottom());
        startTimeLinear.setY(-startTimeLinear.getBottom());
    }


}
