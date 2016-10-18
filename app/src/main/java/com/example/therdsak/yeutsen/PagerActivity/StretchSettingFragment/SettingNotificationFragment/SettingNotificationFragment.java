package com.example.therdsak.yeutsen.PagerActivity.StretchSettingFragment.SettingNotificationFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.therdsak.yeutsen.R;

import java.text.BreakIterator;
import java.util.List;

/**
 * Created by Therdsak on 10/5/2016.
 */
public class SettingNotificationFragment extends Fragment{

    private static final String KEY_URL = "SettingNotificationFragment";
    private Toolbar toolbarNotification;
    private NotificationSounds mNotificationSounds;
    private Switch mSwitch;
    private RecyclerView mRecyclerView;
    private RadioButton lastChecked = null;
    private  int lastCheckedPos = 0;

    public static SettingNotificationFragment newInstance() {

        Bundle args = new Bundle();

        SettingNotificationFragment fragment = new SettingNotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private static final String TAG = "Notification" ;
    String mUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotificationSounds = new NotificationSounds(getActivity());

//        if(getArguments() != null) {
//            mUrl = getArguments().getString(KEY_URL);
//            Log.d(TAG, "Get URL : " + mUrl);
//        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.notification,container,false);



        toolbarNotification = (Toolbar) view.findViewById(R.id.toolbar_notification);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarNotification);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Notification");


        mSwitch = (Switch) view.findViewById(R.id.setting_stretching_switch_message);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true){
                    mRecyclerView.setAdapter(new SoundAdapter(mNotificationSounds.getSounds()));
                    Log.d(TAG, "onCheckedChanged: ");
//                    if(mUrl != null) {
//                        new DownloadFile().execute(mUrl);
//                    }
                }else{
                    mRecyclerView.setAdapter(null);
                }
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.notification_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        if(mUrl != null) {
//            new DownloadFile().execute(mUrl);
//        }


        return view;

    }

    private   class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;

            Log.d(TAG, "SoundAdapter: " + mSounds);

            Log.d(TAG, "SoundAdapter: " + mSounds.size());

        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(final SoundHolder holder, final int position) {
            Sound sound = mSounds.get(position);
            holder.bindSound(sound);
            holder.mButton.setChecked(mSounds.get(position).isSelected());
            holder.mButton.setTag(new Integer(position));

            if(position == 0 && mSounds.get(0).isSelected() && holder.mButton.isChecked()){
                lastChecked = holder.mButton;
                lastCheckedPos = 0;
            }
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

    private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RadioButton mButton ;
        private Sound mSound;

        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.list_item_sound, container, false));
            mButton = (RadioButton) itemView.findViewById(R.id.List_item_sound_button);
            mButton.setOnClickListener(this);
        }

        public void bindSound(Sound sound){
            mSound = sound;
            mButton.setText(mSound.getName());

        }

        @Override
        public void onClick(View view) {
            mNotificationSounds.play(mSound);
            RadioButton rd = (RadioButton) view;
            int clickedPos = ((Integer)rd.getTag()).intValue();

            if(rd.isChecked()){
                if(lastChecked != null){
                    lastChecked.setChecked(false);

                }
                lastChecked = rd;
                lastCheckedPos = clickedPos;
            }
            else
                lastChecked = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNotificationSounds.release();

    }

}









