package com.example.therdsak.yeutsen.PagerActivity.ShowFragment;

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
public class ShowListStretchingFragment extends Fragment {
    public static ShowListStretchingFragment newInstance() {

        Bundle args = new Bundle();

        ShowListStretchingFragment fragment = new ShowListStretchingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_recycler_view,container,false);


        return view;
    }
}
