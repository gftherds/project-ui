package com.example.therdsak.yeutsen.PagerActivity.SettingFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.therdsak.yeutsen.R;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class SettingAboutFragment extends Fragment{

    public static SettingAboutFragment newInstance() {

        Bundle args = new Bundle();

        SettingAboutFragment fragment = new SettingAboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_about,container,false);

        return view;

    }
}
