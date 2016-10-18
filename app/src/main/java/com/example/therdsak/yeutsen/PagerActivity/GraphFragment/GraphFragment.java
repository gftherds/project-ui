package com.example.therdsak.yeutsen.pageractivity.graphfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.therdsak.yeutsen.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

/**
 * Created by Noppharat on 10/11/2016.
 */

public class GraphFragment extends Fragment {
    
    private static final String TAG = "GraphFragment";

    private GraphView graphView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graph_fragment, container, false);
        graphView = (GraphView) view.findViewById(R.id.graph);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 0), //set space
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 5),
                new DataPoint(6, 1),
                new DataPoint(7, 2),
                new DataPoint(8, 0) //set space
        });


        series.setSpacing(20); //space between graph
        graphView.addSeries(series);//add datapoint to graph

        //set string horizontal label
        StaticLabelsFormatter staticLabelFormatter = new StaticLabelsFormatter(graphView);
        staticLabelFormatter.setHorizontalLabels(new String[]{"","S", "M", "T", "W", "Th", "F", "Sa", ""});
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelFormatter);

        //graph label
        graphView.setTitle("TestGraph");

        Log.d(TAG, "onCreateView: ");
        return view;
    }
}
