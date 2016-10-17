package com.example.therdsak.yeutsen.PagerActivity.ShowFragment;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.ImageView;

import com.example.therdsak.yeutsen.MainActivity.TimeDialogFragment;
import com.example.therdsak.yeutsen.PagerActivity.PagerFragment;
import com.example.therdsak.yeutsen.R;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class ShowStretchingFragment extends Fragment{

    private static final String DIALOG_TIME = "ShowStretchingFragment";
    private static final int FIRST_BTN = 1;
    ImageView imageView;
    FragmentManager fm;




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

        fm = getActivity().getSupportFragmentManager();



        imageView = (ImageView) view.findViewById(R.id.show_stretching_image_view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                DialogShowListStretchingFragment dialogFragment = DialogShowListStretchingFragment.newInstance();
                dialogFragment.setTargetFragment(ShowStretchingFragment.this, FIRST_BTN);
                dialogFragment.show(fm, DIALOG_TIME);

            }
        });

        return view;
    }
}
