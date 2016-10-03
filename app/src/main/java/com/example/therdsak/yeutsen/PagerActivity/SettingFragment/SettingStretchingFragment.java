package com.example.therdsak.yeutsen.PagerActivity.SettingFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.therdsak.yeutsen.MainActivity.RegisterFragment;
import com.example.therdsak.yeutsen.MainActivity.TutorialFragment;
import com.example.therdsak.yeutsen.R;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class SettingStretchingFragment extends Fragment{
    ListView listView;
    CustomAdapter adapter;
    String list[];
    FragmentManager fm;
    public SettingStretchingFragment() {

    }

    public static SettingStretchingFragment newInstance() {

        Bundle args = new Bundle();

        SettingStretchingFragment fragment = new SettingStretchingFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getActivity().getSupportFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three,container,false);
        int[] resId = {R.drawable.time, R.drawable.book, R.drawable.alarm, R.drawable.avatar, R.drawable.graph};

        list = new String[]{"Set working time", "Tutorial", "Notification", "About", "Summary"};

        adapter = new CustomAdapter(getActivity(), list, resId);


        listView = (ListView) view.findViewById(R.id.setting_stretching_list_view);
        if (adapter == null) {

        } else {

            listView.setAdapter(adapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment f1 =  SettingNotificationFragment.newInstance();
                fm.beginTransaction().replace(R.id.fragment_container,f1).commit();
            }
        });






        return view;
    }

    private class CustomAdapter extends BaseAdapter{
        Context mContext;
        String[] strName;
        int[] resId;

        public CustomAdapter(Context context,String[] strName, int[] resId){
            this.mContext = context;
            this.strName = strName;
            this.resId = resId;
        }




        @Override
        public int getCount() {
            return strName.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (view == null) {
                view = mInflater.inflate(R.layout.setting_holder_fragment, viewGroup, false);

                TextView textView = (TextView) view.findViewById(R.id.setting_stretching_text_view);
                textView.setText(strName[i]);

                ImageView imageView = (ImageView) view.findViewById(R.id
                        .setting_stretching_image_view);
                imageView.setBackgroundResource(resId[i]);

            }
            return view;
        }
    }
}
