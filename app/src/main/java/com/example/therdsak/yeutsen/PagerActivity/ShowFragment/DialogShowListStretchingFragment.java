package com.example.therdsak.yeutsen.pageractivity.showfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
 * Created by Therdsak on 10/16/2016.
 */
public class DialogShowListStretchingFragment extends DialogFragment {


    private Button buttonCancel;
    private FragmentManager fm;
    private RecyclerView mRecyclerView;
    private StretchAdapter mAdapter;
    private String jsonFileName = "stretch.json";
    private String stretchPhotoFolder = "test.photosgg";
    private ArrayList<HashMap<String, String>> stretchShowList;


    public static DialogShowListStretchingFragment newInstance() {

        Bundle args = new Bundle();

        DialogShowListStretchingFragment fragment = new DialogShowListStretchingFragment();
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

                stretchShowList.add(hashMapList);
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
        stretchShowList = new ArrayList<>();
        mAdapter = new StretchAdapter(stretchShowList);
        jsonStringToList(jsonFileToString(jsonFileName));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        fm = getActivity().getSupportFragmentManager();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.show_recycler_view,null);
        builder.setView(view);
        builder.setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("Cancel",null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.show_stretching_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);


        return builder.show();
    }
    private class StretchHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mDetail;
        private ImageView mShow;
        private String sname;


        public StretchHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.show_holder_title_text_view);
            mDetail = (TextView) itemView.findViewById(R.id.show_holder_detail_text_view);
            mShow = (ImageView) itemView.findViewById(R.id.show_holder_image_view);
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


        protected void setStretchName(String stringName){
            mName.setText(stringName);
        }

        protected void setSName(String sname){
            this.sname = sname;
        }
    }


    private class StretchAdapter extends RecyclerView.Adapter<StretchHolder>{
        ArrayList<HashMap<String, String>> _stretchShowList;

        protected StretchAdapter(ArrayList<HashMap<String, String>> stretchShowList){
            _stretchShowList = stretchShowList;
        }

        @Override
        public StretchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.holder_show,parent,false);
            return new StretchHolder(view);
        }

        @Override
        public void onBindViewHolder(StretchHolder holder, int position) {
            try {

                InputStream inputStream = getActivity().getAssets().open(stretchPhotoFolder + File.separator + _stretchShowList.get(position).get("spath"));
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                holder.bindBitmap(bitmap);
            }catch(Exception e){
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.giphy, null);
                holder.bindDrawable(drawable);
            }
            holder.setStretchName(_stretchShowList.get(position).get("sname"));
            holder.setStretchDetail(_stretchShowList.get(position).get("sname"));
            holder.setSName(_stretchShowList.get(position).get("sname"));
        }


        @Override
        public int getItemCount() {
            return _stretchShowList.size();
        }
    }
}

