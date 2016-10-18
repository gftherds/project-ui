package com.example.therdsak.yeutsen.pageractivity.stretchsettingfragment.settingnotificationfragment;

/**
 * Created by Therdsak on 10/16/2016.
 */
public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer mSoundId;


    public Sound(String assetPath){
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length-1];
        mName = filename.replace(".wav","");
    }

    public String getAssetPath(){
        return mAssetPath;
    }

    public String getName(){
        return mName;
    }


    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer mSoundId) {
        this.mSoundId = mSoundId;
    }


    public boolean isSelected() {
        return false;
    }

    public void setSelected(boolean b) {

    }
}


