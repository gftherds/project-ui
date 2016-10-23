package com.example.therdsak.yeutsen.pageractivity.stretchlistfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.pageractivity.VisibleFragment;

import java.io.File;

/**
 * Created by Noppharat on 10/7/2016.
 */

public class StretchInfoFragment extends VisibleFragment {
    private Toolbar stretchToolbar;
    private ImageView stretchPhoto;
    private TextView stretchInfo;
    private WebView stretchWebView;

    private String stretchNameString;
    private String stretchFileName;
    private String stretchInfoString;
    private String assetPath = "file:///android_asset";
    private String stretchPhotoFolder = "stretches";

    private static final String TAG = "StretchInfoFragment";

    public static StretchInfoFragment newInstance(String sname, String spath, String sinfo){
        Bundle args = new Bundle();
        StretchInfoFragment fragment = new StretchInfoFragment();
        args.putString("sname", sname);
        args.putString("spath", spath);
        args.putString("sinfo", sinfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stretchNameString = getArguments().getString("sname");
        stretchFileName = getArguments().getString("spath");
        stretchInfoString = getArguments().getString("sinfo");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stretch_info_fragment, container, false);

        stretchWebView = (WebView) view.findViewById(R.id.stretch_info_gif);
        stretchToolbar = (Toolbar) view.findViewById(R.id.toolbar_notification);

        ((AppCompatActivity)getActivity()).setSupportActionBar(stretchToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Item");

        stretchInfo = (TextView) view.findViewById(R.id.stretch_info);
        stretchInfo.setText(stretchInfoString);
//        stretchToolbar = (Toolbar) view.findViewById(R.id.toolbar_notification);
        stretchToolbar.setTitle(stretchNameString);

        stretchWebView.loadUrl(assetPath + File.separator + stretchPhotoFolder + File.separator + stretchFileName + ".gif");
        Log.d(TAG, "onCreateView: " + assetPath + File.separator + stretchFileName);

        return view;
    }
}
