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

import com.example.therdsak.yeutsen.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

/**
 * Created by Therdsak on 9/29/2016.
 */
public class SummaryStretchingFragment extends Fragment {
    private static final String TAG = "SummaryStretch";
    private TextView weeklyLabel;
    private GraphView weeklyGraphView;


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

        weeklyLabel = (TextView) view.findViewById(R.id.weekly_textview);

        weeklyGraphView = (GraphView) view.findViewById(R.id.graph);
        StaticLabelsFormatter staticLabelFormatter = new StaticLabelsFormatter(weeklyGraphView);
        staticLabelFormatter.setHorizontalLabels(new String[]{"","S", "M", "T", "W", "Th", "F", "Sa", ""});
        weeklyGraphView.getGridLabelRenderer().setLabelFormatter(staticLabelFormatter);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weeklyGraphView.addSeries(setWeeklyGraph());
    }
}
