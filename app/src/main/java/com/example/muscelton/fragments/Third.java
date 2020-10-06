package com.example.muscelton.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muscelton.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Third extends Fragment { //aka Goals aka Tavoitteet aka Ennuste

    private View rootView;
    public Third() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_third, container, false);

        LineChart lc = rootView.findViewById(R.id.lineChartHistory);
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "Data set 1");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData data = new LineData(dataSets);
        lc.setData(data);
        lc.invalidate();

        return rootView;
    }

    private ArrayList<Entry> dataValues1() {
        ArrayList<Entry> dataVals = new ArrayList<>();
        dataVals.add(new Entry(0, 20));
        dataVals.add(new Entry(1, 40));
        dataVals.add(new Entry(2, 10));
        dataVals.add(new Entry(3, 80));
        dataVals.add(new Entry(4, 90));
        return dataVals;
    }

}
