package com.example.therdsak.yeutsen.PagerActivity.ShowFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.example.therdsak.yeutsen.Database.StretchLog;
import com.example.therdsak.yeutsen.Database.StretchLogLab;
import com.example.therdsak.yeutsen.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class ShowStretchingFragment extends Fragment{
    
    private static final String TAG = "ShowStretchingFragment";

    private Button addButton;
    private WebView randomStretch;

    private int randInt;
    private String assetPath = "file:///android_asset";
    private String stretchPhotoFolder = "stretch";
    private String jsonFileName = "stretch.json";
    private StretchLog stretchLog;
    private ArrayList<HashMap<String, String>> stretchList;


    public static ShowStretchingFragment newInstance() {

        Bundle args = new Bundle();

        ShowStretchingFragment fragment = new ShowStretchingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void jsonStringToList(String jsonString){
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("stretch");
            HashMap<String, String> hashMapList;

            for(int i = 0 ; i < jsonArray.length() ; i++){
                JSONObject jsonObjectInside = jsonArray.getJSONObject(i);
                String stretchName = jsonObjectInside.getString("sname");
                String stretchPath = jsonObjectInside.getString("spath");

                hashMapList = new HashMap<>();
                hashMapList.put("sname", stretchName);
                hashMapList.put("spath", stretchPath);

                stretchList.add(hashMapList);
            }
        }catch(Exception e){

        }
    }


    private String jsonFileToString(String jsonFileName){
        String jsonString;
        try{
            InputStream inputStream = getActivity().getAssets().open(jsonFileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
            return jsonString;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stretchList = new ArrayList<>();
        jsonStringToList(jsonFileToString(jsonFileName));

        Random random = new Random();
        randInt = Math.abs(random.nextInt()) % 15;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container,false);

        randomStretch = (WebView) view.findViewById(R.id.random_stretch_gif);
        randomStretch.loadUrl(assetPath + File.separator + stretchPhotoFolder + File.separator + stretchList.get(randInt).get("spath"));
        addButton = (Button) view.findViewById(R.id.show_stretching_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stretchLog = new StretchLog();
                stretchLog.setUserid("0");
                stretchLog.setStretchid(5);
                stretchLog.setDate(new Date());
                StretchLogLab.getInstance(getActivity()).insertStretchLog(stretchLog);
                Log.d(TAG, "onClick: ID : " + stretchLog.getId() + " USERID :  " + stretchLog.getUserid() + " DATE : " + stretchLog.getDate() + " STRETCHID : "  + stretchLog.getStretchid());
            }
        });

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            Log.d(TAG, "setUserVisibleHint: -----------------true-------------------");

        }else {
            Log.d(TAG, "setUserVisibleHint: -----------------false-------------------");
        }
    }
}
