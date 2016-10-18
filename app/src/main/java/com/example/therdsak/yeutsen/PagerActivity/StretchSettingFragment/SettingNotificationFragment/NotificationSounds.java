package com.example.therdsak.yeutsen.PagerActivity.StretchSettingFragment.SettingNotificationFragment;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Therdsak on 10/16/2016.
 */
public class NotificationSounds {


    private static final int MAX_SOUNDS = 5;
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private AssetManager mAssets;
    private SoundPool mSoundPool;
    private List<Sound> mSounds = new ArrayList<>();



    public NotificationSounds(Context context){
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0 );
        loadSounds();

    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(soundId == null){
            return;
        }
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
    }

    public void release(){
        mSoundPool.release();
    }


    public List<Sound> getSounds() {
        return mSounds;
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
        }catch (IOException ioe){
            return;
        }
        mSounds = new ArrayList<>();
        for(String filename : soundNames){
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            }catch (IOException ioe){

            }
        }
    }

    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd,1);
        sound.setSoundId(soundId);
    }

}
