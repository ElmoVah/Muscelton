package com.example.muscelton.fragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.muscelton.DateStatsActivity;
import com.example.muscelton.R;
import com.example.muscelton.hitech.ExerciseData;
import com.example.muscelton.hitech.Global;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second extends Fragment {

    private static View rootView;
    private static ListView dates;
    private static ArrayList<String> items;
    public Second() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_second, container, false);

        items = new ArrayList<>();

        dates = rootView.findViewById(R.id.listViewDates);
        dates.setAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                items));

        dates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DateStatsActivity.class);
                intent.putExtra("listIndex", i);
                intent.putExtra("titleString", items.get(i));
                startActivity(intent);
            }
        });

        rootView.findViewById(R.id.buttonClearToday).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.getInstance().setRepetitions(new int[ExerciseData.count]);
                updateUI();
            }
        });
        rootView.findViewById(R.id.buttonClearHistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.getInstance().initializeHistory();
                updateUI();
            }
        });
        rootView.findViewById(R.id.buttonOverwriteHistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.getInstance().overwriteRandomData();
                updateUI();
            }
        });
        updateUI();
        return rootView;
    }

    public static void updateUI() {
        if(rootView == null) return;
        ArrayList<int[]> repetitionHistory = Global.getInstance().getRepetitionHistory();
        int showDays = (int)Global.getInstance().getDayCount();//(int)Math.min(30, Global.getInstance().getDayCount()); //max 30, mut ei toiminu :D
        SimpleDateFormat sdf = new SimpleDateFormat("d.M.yyyy");
        Calendar c = Calendar.getInstance();
        items.clear();
        items.add( "Today's repetitions: " + Arrays.stream(Global.getInstance().getRepetitions()).sum());
        for(int i = showDays - 1; i >= 0; i--) {
            c.add(Calendar.DATE, -1);
            items.add(sdf.format(c.getTime()) + " (day " + (i + 1) + "): "
                    + Arrays.stream(repetitionHistory.get(i)).sum() + " reps");

        }
        Log.d("lmao", items.size() + " days refresh");
        ((ArrayAdapter<String>)dates.getAdapter()).notifyDataSetChanged();
    }



}
