package com.example.therdsak.yeutsen.pageractivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;

import com.example.therdsak.yeutsen.R;

import java.util.ArrayList;

/**
 * Created by Therdsak on 10/5/2016.
 */
public class SettingNotificationFragment extends Fragment {

    Toolbar toolbarNotification;
    public static SettingNotificationFragment newInstance() {

        Bundle args = new Bundle();

        SettingNotificationFragment fragment = new SettingNotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private static final String TAG = "Notification" ;
    TextView TimeText;
    TextView Ringtone;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.notification,container,false);

        toolbarNotification = (Toolbar) view.findViewById(R.id.toolbar_notification);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarNotification);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Notification");



//        TimeText = (TextView) view.findViewById(R.id.setting_stretching_text_time_style_ring_tone);
//        TimeText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager manager = getFragmentManager();
//                ChoiceDialogFragment dialog = new ChoiceDialogFragment();
//
//                Bundle bundle = new Bundle();
//                bundle.putStringArrayList(ChoiceDialogFragment.DATA, getItems());     // Require ArrayList
//                bundle.putInt(ChoiceDialogFragment.SELECTED, 0);
//                dialog.setArguments(bundle);
//                dialog.show(manager, "Dialog");
//            }
//        });


        Ringtone = (TextView) view.findViewById(R.id.setting_stretching_text_ring_tone);
        Ringtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                ChoiceDialogFragment dialog = new ChoiceDialogFragment();

                Bundle bundle = new Bundle();
                bundle.putStringArrayList(ChoiceDialogFragment.DATA, getTones());     // Require ArrayList
                bundle.putInt(ChoiceDialogFragment.SELECTED, 0);
                dialog.setArguments(bundle);
                dialog.show(manager, "Dialog");

            }
        });



        return view;



    }

    private ArrayList<String> getTones(){
        ArrayList<String> sound_val = new ArrayList<String>();

        sound_val.add("love");
        sound_val.add("pop");
        sound_val.add("classic");
        sound_val.add("rock");
        sound_val.add("ska");
        sound_val.add("indy");
        return sound_val;
    }



    private ArrayList<String> getItems()
    {
        ArrayList<String> ret_val = new ArrayList<String>();

        ret_val.add("15 min");
        ret_val.add("30 min");
        ret_val.add("45 min");
        return ret_val;
    }




}
