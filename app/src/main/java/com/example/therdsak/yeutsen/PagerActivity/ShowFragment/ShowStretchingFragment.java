package com.example.therdsak.yeutsen.PagerActivity.ShowFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.therdsak.yeutsen.R;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class ShowStretchingFragment extends Fragment{


    public static ShowStretchingFragment newInstance() {

        Bundle args = new Bundle();

        ShowStretchingFragment fragment = new ShowStretchingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container,false);

        return view;
    }
}
