package com.example.muscelton.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.muscelton.R;
import com.example.muscelton.hitech.Exercise;
import com.example.muscelton.hitech.ExerciseData;
import com.example.muscelton.hitech.Global;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class First extends Fragment {

    final Random random = new Random();
    private View rootView;


    public First() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false);
        rootView = inflater.inflate(R.layout.fragment_first, container, false);

        final TextView[] eTextViews = {
                rootView.findViewById(R.id.textViewExercise1),
                rootView.findViewById(R.id.textViewExercise2),
                rootView.findViewById(R.id.textViewExercise3),
                rootView.findViewById(R.id.textViewExercise4),
                rootView.findViewById(R.id.textViewExercise5),
        };
        final ImageButton[] rerolls = new ImageButton[] {
                rootView.findViewById(R.id.imageButtonReroll1),
                rootView.findViewById(R.id.imageButtonReroll2),
                rootView.findViewById(R.id.imageButtonReroll3),
                rootView.findViewById(R.id.imageButtonReroll4),
                rootView.findViewById(R.id.imageButtonReroll5),
        };
        final EditText[] editTexts = new EditText[] {
                rootView.findViewById(R.id.editTextReps1),
                rootView.findViewById(R.id.editTextReps2),
                rootView.findViewById(R.id.editTextReps3),
                rootView.findViewById(R.id.editTextReps4),
                rootView.findViewById(R.id.editTextReps5),
        };

        ((RadioGroup) rootView.findViewById(R.id.radioGroupDifficulty)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Global.getInstance().setDifficulty(i == R.id.radioButtonEasy ? 0 : i == R.id.radioButtonNormal ? 1 : 2);
                //This is auto update on change difficulty, not necessary
                Exercise[] exercises = Global.getInstance().renewExercises();
                int[] reps = Global.getInstance().getRepetitions();
                for(int j = 0; j < eTextViews.length; j++) {
                    eTextViews[j].setText(ExerciseData.names[exercises[j].ordinal()]);
                    editTexts[j].setText(String.valueOf(reps[exercises[j].ordinal()]));
                }


            }
        });

        ((Button) rootView.findViewById(R.id.buttonGenerate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise[] exercises = Global.getInstance().renewExercises();
                for(int i = 0; i < eTextViews.length; i++)
                    eTextViews[i].setText(ExerciseData.names[exercises[i].ordinal()]);
            }
        });

        for(int i = 0; i < rerolls.length; i++ ) {
            final int a = i; //haxi :D
            rerolls[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Exercise e = Global.getInstance().renewExercise(a);
                    eTextViews[a].setText(ExerciseData.names[e.ordinal()]);
                    editTexts[a].setText(String.valueOf(Global.getInstance().getRepetitions()[e.ordinal()]));
                }
            });
        }

        ((Button) rootView.findViewById(R.id.buttonSave)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] reps = new int[5];
                for(int i = 0; i < reps.length; i++) {
                    reps[i] = Integer.valueOf(editTexts[i].getText().toString());
                }
                Global.getInstance().setRepetitions(reps);
            }
        });
        return rootView;
    }
}
