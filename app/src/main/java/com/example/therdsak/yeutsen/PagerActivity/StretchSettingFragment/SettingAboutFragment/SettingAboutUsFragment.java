package com.example.therdsak.yeutsen.pageractivity.stretchsettingfragment.settingaboutfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.therdsak.yeutsen.R;

/**
 * Created by Therdsak on 10/10/2016.
 */
public class SettingAboutUsFragment extends Fragment {
    Toolbar toolbarAboutUs;

    public static SettingAboutUsFragment newInstance() {

        Bundle args = new Bundle();

        SettingAboutUsFragment fragment = new SettingAboutUsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_us_fragment,container,false);

        toolbarAboutUs = (Toolbar) view.findViewById(R.id.toolbar_about_us);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarAboutUs);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("About Us");

        return view;
    }
}
