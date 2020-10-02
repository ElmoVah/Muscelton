package com.example.muscelton;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class First extends Fragment {


    public First() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);

        final Random random = new Random();

        Button buttonGenerateWorkout = findViewById(R.id.buttonGeneroi);
        buttonGenerateWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> workoutSheet = Global.getInstance().getWorkoutSheet(getDifficulty());
                Collections.shuffle(workoutSheet);
                ((TextView)findViewById(R.id.textViewHarjoitus1))
                        .setText(workoutSheet.get(0));
                ((TextView)findViewById(R.id.textViewHarjoitus2))
                        .setText(workoutSheet.get(1));
                ((TextView)findViewById(R.id.textViewHarjoitus3))
                        .setText(workoutSheet.get(2));
                ((TextView)findViewById(R.id.textViewHarjoitus4))
                        .setText(workoutSheet.get(3));
                ((TextView)findViewById(R.id.textViewHarjoitus5))
                        .setText(workoutSheet.get(4));

            }
        });

        ImageButton reroll1 = findViewById(R.id.imageButtonReroll1);
        reroll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = random.nextInt(Global.getInstance().getWorkoutSheet(getDifficulty()).size());
                List<String> workoutSheet = Global.getInstance().getWorkoutSheet(getDifficulty());
                ((TextView)findViewById(R.id.textViewHarjoitus1))
                        .setText(workoutSheet.get(i));
            }
        });

        ImageButton reroll2 = findViewById(R.id.imageButtonReroll2);
        reroll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = random.nextInt(Global.getInstance().getWorkoutSheet(getDifficulty()).size());
                List<String> workoutSheet = Global.getInstance().getWorkoutSheet(getDifficulty());
                ((TextView)findViewById(R.id.textViewHarjoitus2))
                        .setText(workoutSheet.get(i));
            }
        });

        ImageButton reroll3 = findViewById(R.id.imageButtonReroll3);
        reroll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = random.nextInt(Global.getInstance().getWorkoutSheet(getDifficulty()).size());
                List<String> workoutSheet = Global.getInstance().getWorkoutSheet(getDifficulty());
                ((TextView)findViewById(R.id.textViewHarjoitus3))
                        .setText(workoutSheet.get(i));
            }
        });

        ImageButton reroll4 = findViewById(R.id.imageButtonReroll4);
        reroll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = random.nextInt(Global.getInstance().getWorkoutSheet(getDifficulty()).size());
                List<String> workoutSheet = Global.getInstance().getWorkoutSheet(getDifficulty());
                ((TextView)findViewById(R.id.textViewHarjoitus4))
                        .setText(workoutSheet.get(i));
            }
        });

        ImageButton reroll5 = findViewById(R.id.imageButtonReroll5);
        reroll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = random.nextInt(Global.getInstance().getWorkoutSheet(getDifficulty()).size());
                List<String> workoutSheet = Global.getInstance().getWorkoutSheet(getDifficulty());
                ((TextView)findViewById(R.id.textViewHarjoitus5))
                        .setText(workoutSheet.get(i));
            }

        });
    }
}
