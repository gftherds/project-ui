package com.example.therdsak.yeutsen.mainactivity.firstset;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.therdsak.yeutsen.R;
import com.example.therdsak.yeutsen.pageractivity.stretchlistfragment.StretchInfoFragment;

/**
 * Created by outnotin on 10/23/2016 AD.
 */

public class WelcomeFragment extends Fragment {

    private ImageView backgroundImage;
    private ImageView welcomeText;
    private Button gettingStartButton;

    public static WelcomeFragment newInstance() {

        Bundle args = new Bundle();

        WelcomeFragment fragment = new WelcomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.welcome_fragment, container, false);
        backgroundImage = (ImageView) v.findViewById(R.id.background_image);
        welcomeText = (ImageView) v.findViewById(R.id.welcome_text);
        gettingStartButton = (Button) v.findViewById(R.id.getting_start_button);

//        backgroundImage.setBackground(getResources().getDrawable(R.drawable.slidein));
//        welcomeText.setBackground(getResources().getDrawable(R.drawable.welcome));
//        gettingStartButton.setBackground(getResources().getDrawable(R.drawable.gettingstartbutton));
        gettingStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetTimeFragment fragment = SetTimeFragment.newInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container2 ,fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }
}
