package com.example.therdsak.yeutsen.PagerActivity.StretchListFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.therdsak.yeutsen.MainActivity.RegisterFragment;
import com.example.therdsak.yeutsen.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Noppharat on 10/7/2016.
 */

public class StretchListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private ArrayList<HashMap<String, String>> stretchList;
    private StretchAdapter mStretchAdapter;
    private FragmentManager fm;

    private int gridSize = 2;
    private String stretchPhotoFolder = "test_photo";
    private String jsonFileName = "stretch.json";



    public static StretchListFragment newInstance(){
        Bundle args = new Bundle();
        StretchListFragment fragment = new StretchListFragment();
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
        mStretchAdapter = new StretchAdapter(stretchList);
        jsonStringToList(jsonFileToString(jsonFileName));
        fm = getActivity().getSupportFragmentManager();




    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stretch_recycler, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.stretch_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), gridSize));
        mRecyclerView.setAdapter(mStretchAdapter);




        return view;
    }

    private class StretchHolder extends RecyclerView.ViewHolder{
        private ImageView mStretchPhoto;
        private TextView mStretchName;
        private String sname;

        public StretchHolder(View itemView) {
            super(itemView);
            mStretchPhoto = (ImageView) itemView.findViewById(R.id.stretch_photo);
            mStretchName = (TextView) itemView.findViewById(R.id.stretch_name);
            mStretchPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                Intent intent = new Intent(getActivity(),StretchInfoActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),mStretchPhoto,"transitionName");
                getActivity().startActivity(intent,options.toBundle());
//                    Fragment fragmentStretchList = StretchInfoFragment.newInstance(sname);
//                    fm.beginTransaction().replace(R.id.fragment_container2,fragmentStretchList).addToBackStack(null).addSharedElement(mStretchPhoto,"Photo").commit();



                }
            });
        }
        protected void bindBitmap(Bitmap bitmap){
            mStretchPhoto.setImageBitmap(bitmap);
        }

        protected void bindDrawable(Drawable drawable){
            mStretchPhoto.setImageDrawable(drawable);
        }

        protected void setStretchName(String stringName){
            mStretchName.setText(stringName);
        }

        protected void setSName(String sname){
            this.sname = sname;
        }
    }

    private class StretchAdapter extends RecyclerView.Adapter<StretchHolder>{
        ArrayList<HashMap<String, String>> _stretchList;

        protected StretchAdapter(ArrayList<HashMap<String, String>> stretchList){
            _stretchList = stretchList;
        }

        @Override
        public StretchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_stretch, parent, false);
            return new StretchHolder(view);
        }

        @Override
        public void onBindViewHolder(StretchHolder holder, int position) {
            try {
                InputStream inputStream = getActivity().getAssets().open(stretchPhotoFolder + File.separator + _stretchList.get(position).get("spath"));
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                holder.bindBitmap(bitmap);
            }catch(Exception e){
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.giphy, null);
                holder.bindDrawable(drawable);
            }
            holder.setStretchName(_stretchList.get(position).get("sname"));
            holder.setSName(_stretchList.get(position).get("sname"));
        }

        @Override
        public int getItemCount() {
            return _stretchList.size();
        }
    }

}
