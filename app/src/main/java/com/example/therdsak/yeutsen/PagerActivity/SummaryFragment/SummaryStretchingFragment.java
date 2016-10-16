package com.example.therdsak.yeutsen.PagerActivity.SummaryFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.therdsak.yeutsen.Database.StretchLog;
import com.example.therdsak.yeutsen.Database.StretchLogLab;
import com.example.therdsak.yeutsen.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.List;

/**
 * Created by Therdsak on 9/29/2016.
 */

public class SummaryStretchingFragment extends Fragment {
    private static final String TAG = "SummaryStretch";
    private GraphView weeklyGraphView;
    private TextView totalStretch;
    private TextView monthlyStretch;
    private TextView weeklyStretch;
    private TextView todayStretch;

    private double[] weekListInfo;

    public static SummaryStretchingFragment newInstance() {

        Bundle args = new Bundle();

        SummaryStretchingFragment fragment = new SummaryStretchingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private BarGraphSeries<DataPoint> setWeeklyGraph(){
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 0), //set space
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 0),
                new DataPoint(6, 0),
                new DataPoint(7, 0),
                new DataPoint(8, 0) //set space
        });
        series.setSpacing(20);
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb(255, 64, 129);
            }
        });
        series.setAnimated(true);
        return series;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_summary,container,false);

        totalStretch = (TextView) view.findViewById(R.id.total_stretches);
        monthlyStretch = (TextView) view.findViewById(R.id.monthly_stretches);
        weeklyStretch = (TextView) view.findViewById(R.id.weekly_stretches);
        todayStretch = (TextView) view.findViewById(R.id.today_stretches);


        weeklyGraphView = (GraphView) view.findViewById(R.id.graph);
        StaticLabelsFormatter staticLabelFormatter = new StaticLabelsFormatter(weeklyGraphView);
        staticLabelFormatter.setHorizontalLabels(new String[]{"","S", "M", "T", "W", "Th", "F", "Sa", ""});
        weeklyGraphView.getGridLabelRenderer().setLabelFormatter(staticLabelFormatter);


        return view;
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: check on resume");
        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
        if(isVisibleToUser == true){
            weeklyGraphView.addSeries(setWeeklyGraph());
            totalStretch.setText(StretchLogLab.getInstance(getContext()).queryTotalStretch());
            monthlyStretch.setText(StretchLogLab.getInstance(getContext()).queryMonthlyStretch());

            todayStretch.setText(StretchLogLab.getInstance(getContext()).queryTodayStretch());
        }else{
            if(weeklyGraphView != null){
                weeklyGraphView.removeAllSeries();
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StretchLogLab.getInstance(getActivity()).queryMonthlyStretch();
//        weeklyGraphView.addSeries(setWeeklyGraph());
    }

}
