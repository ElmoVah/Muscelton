package com.example.muscelton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.muscelton.hitech.ExerciseData;
import com.example.muscelton.hitech.Global;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DateStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_stats);
        int listIndex = getIntent().getIntExtra("listIndex", 0);
        String titleString = getIntent().getStringExtra("titleString");

        ArrayList<int[]> repetitionHistory = Global.getInstance().getRepetitionHistory();

        int[] dayReps = listIndex == 0 ? Global.getInstance().getRepetitions()
                : Global.getInstance().getRepetitionHistory().get(repetitionHistory.size() - listIndex);

        ArrayList<String> items = new ArrayList<>();

        for(int i = 0; i < dayReps.length; i++) {
            int r = dayReps[i];
            if(r != 0) {
                items.add(ExerciseData.names[i] + ", " + r);
            }
        }

        ((TextView)findViewById(R.id.textViewInfo)).setText(titleString);
        ((ListView)findViewById(R.id.listViewRepCounts)).setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items));

    }
}