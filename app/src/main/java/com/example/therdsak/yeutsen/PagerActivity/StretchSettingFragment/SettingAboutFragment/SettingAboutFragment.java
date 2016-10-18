package com.example.therdsak.yeutsen.pageractivity.stretchsettingfragment.settingaboutfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.pageractivity.VisibleFragment;

/**
 * Created by Therdsak on 10/5/2016.
 */
public class SettingAboutFragment extends VisibleFragment {

    TextView textViewAbout;
    FragmentManager fm;
    Toolbar toolbarAbout;
    public static SettingAboutFragment newInstance() {

        Bundle args = new Bundle();

        SettingAboutFragment fragment = new SettingAboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fm = getActivity().getSupportFragmentManager();

        View view = inflater.inflate(R.layout.setting_about,container,false);

        toolbarAbout = (Toolbar) view.findViewById(R.id.toolbar_about);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarAbout);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("About");


        textViewAbout = (TextView) view.findViewById(R.id.about_us);
        textViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentSettingAbout = SettingAboutUsFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragment_container2,fragmentSettingAbout).addToBackStack(null).commit();
            }
        });


        return view;
    }
}
