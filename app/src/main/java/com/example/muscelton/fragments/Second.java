package com.example.muscelton.fragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.muscelton.DateStatsActivity;
import com.example.muscelton.R;
import com.example.muscelton.hitech.Global;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second extends Fragment {

    private View secondView;
    private static ListView dates;
    private static ArrayList<String> items;
    public Second() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       secondView = inflater.inflate(R.layout.fragment_second, container, false);

        ArrayList<int[]> repetitionHistory = Global.getInstance().getRepetitionHistory();
        int showDays = (int)Math.min(30, Global.getInstance().getDayCount()); //max 30

        items = new ArrayList<>();
        items.add("Today's repetitions: ?");
        for(int i = showDays - 1; i >= 0; i--) {
            items.add("Day " + (i + 1) +" reps: " + Arrays.stream(repetitionHistory.get(i)).sum());
        }


        dates = secondView.findViewById(R.id.listViewDates);
        dates.setAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                items));

        dates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DateStatsActivity.class);
                intent.putExtra("index", i);
                startActivity(intent);
            }
        });
        return secondView;
    }

    public static void updateTodayItem() {
        items.set(0, "Today's repetitions " + Arrays.stream(Global.getInstance().getRepetitions()).sum());
        ((ArrayAdapter<String>)dates.getAdapter()).notifyDataSetChanged();
    }


}
