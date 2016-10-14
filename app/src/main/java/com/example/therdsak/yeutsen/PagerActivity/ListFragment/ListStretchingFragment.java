package com.example.therdsak.yeutsen.pageractivity.listfragment;

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
public class ListStretchingFragment extends Fragment {


    public static ListStretchingFragment newInstance() {

        Bundle args = new Bundle();

        ListStretchingFragment fragment = new ListStretchingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_two,container,false);

        return view;

    }
}

