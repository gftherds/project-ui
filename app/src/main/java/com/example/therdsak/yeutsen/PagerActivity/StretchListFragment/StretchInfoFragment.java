package com.example.therdsak.yeutsen.pageractivity.stretchlistfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.therdsak.yeutsen.R;

import java.io.File;

/**
 * Created by Noppharat on 10/7/2016.
 */

public class StretchInfoFragment extends Fragment {
    private Toolbar stretchToolbar;
    private ImageView stretchPhoto;
    private TextView stretchName;
    private TextView stretchInfo;
    private WebView stretchWebView;

    private String stretchNameString;
    private String stretchFileName;
    private String assetPath = "file:///android_asset";
    private String stretchPhotoFolder = "stretch";
    
    private static final String TAG = "StretchInfoFragment";

    public static StretchInfoFragment newInstance(String sname, String spath){
        Bundle args = new Bundle();
        StretchInfoFragment fragment = new StretchInfoFragment();
        args.putString("sname", sname);
        args.putString("spath", spath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stretchNameString = getArguments().getString("sname");
        stretchFileName = getArguments().getString("spath");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stretch_info_fragment, container, false);

        stretchWebView = (WebView) view.findViewById(R.id.stretch_info_webview);
        stretchName = (TextView) view.findViewById(R.id.stretch_info_name);
        stretchName.setText(stretchNameString);
        stretchInfo = (TextView) view.findViewById(R.id.stretch_info);
        stretchToolbar = (Toolbar) view.findViewById(R.id.toolbar_notification);
        stretchToolbar.setTitle(stretchNameString);

        stretchWebView.loadUrl(assetPath + File.separator + stretchPhotoFolder + File.separator + stretchFileName);
        Log.d(TAG, "onCreateView: " + assetPath + File.separator + stretchFileName);

        return view;
    }
}
