package com.example.therdsak.yeutsen.mainactivity.firstset;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.pageractivity.PagerFragment;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by outnotin on 10/23/2016 AD.
 */

public class SetDayAndLengthFragment extends Fragment {

    private Button backButton;
    private Button finishButton;

    private CheckBox sunday;
    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;

    private TextView setAlertLength;
    private DiscreteSeekBar alertSeekLength;

    public static SetDayAndLengthFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SetDayAndLengthFragment fragment = new SetDayAndLengthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.day_and_length_fragment, container, false);

        sunday = (CheckBox) v.findViewById(R.id.check_sunday);
        monday = (CheckBox) v.findViewById(R.id.check_monday);
        tuesday = (CheckBox) v.findViewById(R.id.check_tuesday);
        wednesday = (CheckBox) v.findViewById(R.id.check_wednesday);
        thursday = (CheckBox) v.findViewById(R.id.check_thursday);
        friday = (CheckBox) v.findViewById(R.id.check_friday);
        saturday = (CheckBox) v.findViewById(R.id.check_saturday);

        alertSeekLength = (DiscreteSeekBar) v.findViewById(R.id.alert_seek);
        alertSeekLength.setProgress(45);
        alertSeekLength.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                setAlertLength.setText("Set alert length : " + alertSeekLength.getProgress() + " minutes");
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        setAlertLength = (TextView) v.findViewById(R.id.set_alert_length_text);
        setAlertLength.setText("Set alert length : " + alertSeekLength.getProgress() + " minutes");

        backButton = (Button) v.findViewById(R.id.day_and_length_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetTimeFragment fragment = SetTimeFragment.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container2 ,fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        finishButton = (Button) v.findViewById(R.id.day_and_length_finish_button);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PagerFragment fragment = PagerFragment.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container2 ,fragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

        return v;
    }
}
