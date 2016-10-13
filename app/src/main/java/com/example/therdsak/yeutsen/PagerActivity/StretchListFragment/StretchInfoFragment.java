package com.example.therdsak.yeutsen.PagerActivity.StretchListFragment;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.therdsak.yeutsen.R;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Noppharat on 10/7/2016.
 */

public class StretchInfoFragment extends Fragment {

    private static final String TAG = "StretchInfoFragment";

    private Toolbar stretchToolbar;
    private TextView stretchName;
    private TextView stretchInfo;
    private WebView stretchWebView;

    private String stretchNameString;
    private String stretchFileName;
    private String assetPath = "file:///android_asset/stretch";

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

        stretchWebView.loadUrl(assetPath + File.separator + stretchFileName);
        Log.d(TAG, "onCreateView: " + assetPath + File.separator + stretchFileName);

        return view;
    }
}
