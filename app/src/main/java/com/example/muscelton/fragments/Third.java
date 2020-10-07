package com.example.muscelton.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muscelton.R;
import com.example.muscelton.hitech.Global;
import com.example.muscelton.hitech.SaveManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;



/**
 * Luokka sisältää Kolmannen välilehden "Progress" komponenttien eventtien määrittelyn ja UI:n päivityksen
 * @author Elias Perttu
 */
public class Third extends Fragment { //aka Goals aka Tavoitteet aka Ennuste

    private static View rootView;
    public Third() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_third, container, false);
        generateGraphs();

        rootView.findViewById(R.id.buttonOverwrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.getInstance().overwriteRandomData();
                generateGraphs();
            }
        });

        return rootView;
    }

    /**
     * Generoi visuaaliset graafit vastaaamaan tämänhetkistä "tietokantaa"
     */
    public static void generateGraphs() {

        if(rootView == null) return;

        ArrayList<int[]> repetitionHistory = Global.getInstance().getRepetitionHistory();
        int shownHistoryDays = (int)Math.min(30, Global.getInstance().getDayCount()); //max 30


        LineChart chartHistory = rootView.findViewById(R.id.lineChartHistory);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        int repsTotal = 0;
        ArrayList<Entry> repEntries = new ArrayList<>();
        ArrayList<Entry> sumEntries = new ArrayList<>();
        for(int i = repetitionHistory.size() - shownHistoryDays; i < repetitionHistory.size(); i++) {
            int repSum = Arrays.stream(repetitionHistory.get(i)).sum(); //historyday
            repsTotal += repSum;
            repEntries.add(new Entry(i + 1, repSum));
            sumEntries.add(new Entry(i + 1, repsTotal));
        }
        int repSum = Arrays.stream(Global.getInstance().getRepetitions()).sum(); //today
        repsTotal += repSum;
        repEntries.add(new Entry(shownHistoryDays +1, repSum));
        sumEntries.add(new Entry(shownHistoryDays +1, repsTotal));
        float avgReps = repsTotal / (shownHistoryDays + 1);

        LineDataSet ds1 = new LineDataSet(repEntries, "Daily reps");
        ds1.setColor(Color.parseColor("#FDD365"));
        dataSets.add(ds1);

        LineDataSet ds2 = new LineDataSet(sumEntries, "Total reps, currently " + avgReps + " reps/day");
        ds2.setColor(Color.parseColor("#FD2EB3"));
        dataSets.add(ds2);

        LineData data = new LineData(dataSets);

        XAxis xAxis = chartHistory.getXAxis();
        xAxis.setGranularity(1.0f);
        xAxis.setLabelCount(shownHistoryDays + 1, true);
        Description d = new Description();
        d.setText("View last " + shownHistoryDays + " days + today");
        chartHistory.setDescription(d);
        chartHistory.setData(data);
        chartHistory.invalidate();

        //Linechart Prediction:

        LineChart chartPrediction = rootView.findViewById(R.id.lineChartPrediction);
        ArrayList<ILineDataSet> dataSets2 = new ArrayList<>();
        ArrayList<Entry> predictionEntries = new ArrayList<>();

        int predictHistoryDays = (int)Math.min(7, Global.getInstance().getDayCount()); //max 7
        int predictHistoryTotal = 0;
        for(int i = repetitionHistory.size() - predictHistoryDays; i < repetitionHistory.size(); i++) {
            predictHistoryTotal += Arrays.stream(repetitionHistory.get(i)).sum();
        }
        predictHistoryTotal +=  Arrays.stream(Global.getInstance().getRepetitions()).sum();
        float avgRepsBase = predictHistoryTotal / (predictHistoryDays + 1);
        for(int i = 0; i < 31; i+=5) {
            predictionEntries.add(new Entry(i, repsTotal + (avgRepsBase * (i))));
        }

        LineDataSet ds3 = new LineDataSet(predictionEntries, "Prediciton of your 7 day average: " + avgRepsBase + " reps/day");
        ds3.setColor(Color.parseColor("#FDD365"));
        dataSets2.add(ds3);
        LineData data2 = new LineData(dataSets2);
        XAxis xAxis2 = chartPrediction.getXAxis();
        xAxis2.setGranularity(1.0f);
        xAxis2.setLabelCount(7, true);
        Description d2 = new Description();
        d2.setText("This is useless.");
        chartPrediction.setDescription(d2);
        chartPrediction.getDescription().setEnabled(false);
        chartPrediction.setData(data2);
        chartPrediction.invalidate();

    }

}
