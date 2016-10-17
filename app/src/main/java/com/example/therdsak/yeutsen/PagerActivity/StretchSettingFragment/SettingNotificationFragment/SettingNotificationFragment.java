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

    Toolbar toolbarNotification;
    private NotificationSounds mNotificationSounds;
    Switch mSwitch;
    RecyclerView mRecyclerView;
    public int mSelectedItem = -1;


    public static SettingNotificationFragment newInstance() {

        Bundle args = new Bundle();

        SettingNotificationFragment fragment = new SettingNotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private static final String TAG = "Notification" ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotificationSounds = new NotificationSounds(getActivity());

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
                }else{
                    mRecyclerView.setAdapter(null);
                }
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.notification_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        return view;

    }

    private  class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
            notifyItemRangeChanged(0,mSounds.size());
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
            holder.mButton.setChecked(position == mSelectedItem);



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
            mSelectedItem = getAdapterPosition();
 //           notifyItemRangeChanged(0, mSound.size());

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNotificationSounds.release();

    }
}









