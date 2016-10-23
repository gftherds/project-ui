package com.example.therdsak.yeutsen.pageractivity.showfragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.therdsak.yeutsen.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Noppharat on 10/17/2016.
 */

public class DialogShowStretchingFragment extends DialogFragment {

    private static final String TAG = "DialogShowFragment";
    private FragmentManager fragmentManager;
    private RecyclerView mRecyclerView;
    private StretchAdapter mAdapter;
    private String jsonFilename = "stretch.json";
    private String stretchPhotoFolder = "stretches";
    private ArrayList<HashMap<String, String>> stretchShowList;
    protected int position;

    public static DialogShowStretchingFragment newInstance(int position){
        Bundle args = new Bundle();
        args.putInt("position", position);
        DialogShowStretchingFragment fragment = new DialogShowStretchingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void jsonStringToList(String jsonString){
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("stretch");
            HashMap<String, String> hashMapList;

            for(int i = 0 ; i < jsonArray.length() ; i++){
                JSONObject jsonObjectInside = jsonArray.getJSONObject(i);
                String stretchName = jsonObjectInside.getString("sname");
                String stretchPath = jsonObjectInside.getString("spath");
                String stretchId = jsonObjectInside.getString("sid");
                String isSelected = jsonObjectInside.getString("isselect");

                hashMapList = new HashMap<>();
                hashMapList.put("sname", stretchName);
                hashMapList.put("spath", stretchPath);
                hashMapList.put("sid", stretchId);
                hashMapList.put("isselect", isSelected);

                stretchShowList.add(hashMapList);
            }
        }catch (Exception e){

        }
    }

    private String jsonFileToString(String jsonFilename){
        String jsonString;
        try{
            InputStream inputStream = getActivity().getAssets().open(jsonFilename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
            return jsonString;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.position = getArguments().getInt("position");
        stretchShowList = new ArrayList<>();
        jsonStringToList(jsonFileToString(jsonFilename));
        stretchShowList.get(position).put("isselect", "true");
        mAdapter = new StretchAdapter(stretchShowList, position);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.show_recycler_view_dialog, null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.show_stretching_recycler_view_dialog);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.invalidate();

        builder.setView(view);
        builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               //send position back to ShowStretchingFragment.java to change stretching in webview
                Intent intent = new Intent();
                intent.putExtra("position", position);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            }
        });
        builder.setNegativeButton("Cancel", null);

        return builder.show();
    }

    private class StretchHolder extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mDetail;
        private ImageView mShow;

        public StretchHolder(final View itemView) {
            super(itemView);
//            mName = (TextView) itemView.findViewById(R.id.show_holder_title_text_view);
            mDetail = (TextView) itemView.findViewById(R.id.show_holder_detail_text_view);
            mShow = (ImageView) itemView.findViewById(R.id.show_holder_image_view);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //send sid to DialogShowStretchingFragment.java to prepare before send to ShowStretchingFragment.java
//                    itemView.setBackgroundColor(Color.TRANSPARENT);
//                    Log.d(TAG, "onClick: Before old position : " + position + " : " + stretchShowList.get(position).get("isselect"));
//                    stretchShowList.get(position).put("isselect", "false");
//                    Log.d(TAG, "onClick: Before new position : " + position + " : " + stretchShowList.get(position).get("isselect"));
//                    position = sid;
//                    Log.d(TAG, "onClick: After old  position : " + position + " : " + stretchShowList.get(position).get("isselect"));
//                    stretchShowList.get(position).put("isselect", "true");
//                    Log.d(TAG, "onClick: After newposition : " + position + " : " + stretchShowList.get(position).get("isselect"));
//                    itemView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//
//                }
//            });

        }

        protected void bindBitmap(Bitmap bitmap){
            mShow.setImageBitmap(bitmap);
        }

        protected void bindDrawable(Drawable drawable){
            mShow.setImageDrawable(drawable);
        }

        protected void setStretchDetail(String stringDetail){
            mDetail.setText(stringDetail);
        }

//        protected void setStretchName(String stretchName){
//            mName.setText(stretchName);
//        }

        protected void setPosition(int bposition){
            position = bposition;
        }

    }

    private class StretchAdapter extends RecyclerView.Adapter<StretchHolder>{
        ArrayList<HashMap<String, String>> _stretchShowList;
        int selected_position;

        protected StretchAdapter(ArrayList<HashMap<String, String>> stretchShowList, int position){
            _stretchShowList = stretchShowList;
            selected_position = position;
        }

        @Override
        public StretchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.holder_show, parent, false);
            return new StretchHolder(view);
        }

        @Override
        public void onBindViewHolder(final StretchHolder holder, final int position) {
            try {
                InputStream inputStream = getActivity().getAssets().open(stretchPhotoFolder + File.separator + _stretchShowList.get(position).get("spath"));

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                holder.bindBitmap(bitmap);
            }catch (Exception e){
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.giphy, null);
                holder.bindDrawable(drawable);
            }
//            holder.setStretchName(_stretchShowList.get(position).get("sname"));
            holder.setStretchDetail(_stretchShowList.get(position).get("sname"));

            Log.d(TAG, "onBindViewHolder: " + _stretchShowList.get(position).get("isselect"));
            
            if(stretchShowList.get(position).get("isselect").equals("true")){
                holder.itemView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stretchShowList.get(selected_position).put("isselect", "false");
                    notifyItemChanged(selected_position);
                    selected_position = Integer.valueOf(_stretchShowList.get(position).get("sid"));
                    stretchShowList.get(selected_position).put("isselect", "true");
                    notifyItemChanged(selected_position);
                    holder.setPosition(selected_position);
                }
            });

        }

        @Override
        public int getItemCount() {
            return _stretchShowList.size();
        }


    }
}
