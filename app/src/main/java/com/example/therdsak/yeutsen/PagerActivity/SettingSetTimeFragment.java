package com.example.therdsak.yeutsen.pageractivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.therdsak.yeutsen.mainactivity.RegisterFragment;
import com.example.therdsak.yeutsen.R;

/**
 * Created by Therdsak on 10/6/2016.
 */
public class SettingSetTimeFragment extends Fragment {

    Button buttonSolve;
    Button buttonCancel;
    FragmentManager fm ;
    Toolbar toolbarSetTime;

    public static SettingSetTimeFragment newInstance() {

        Bundle args = new Bundle();

        SettingSetTimeFragment fragment = new SettingSetTimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_set_time,container,false);

        toolbarSetTime = (Toolbar) view.findViewById(R.id.toolbar_set_time);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarSetTime);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Set Time");



        fm = getActivity().getSupportFragmentManager();

        buttonSolve = (Button) view.findViewById(R.id.button_solve);
        buttonSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f5 = RegisterFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragment_container5,f5).commit();
            }
        });

        buttonCancel = (Button) view.findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f6 = new PagerFragment();
                fm.beginTransaction().replace(R.id.fragment_container5,f6).commit();
            }
        });
        return view;
    }
}
