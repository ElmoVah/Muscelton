package com.example.muscelton;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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

        Button buttonGenerateWorkout = (Button) rootView.findViewById(R.id.buttonGenerate);
        buttonGenerateWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise[] exercises = Global.getInstance().renewExercises();
                ((TextView)getView().findViewById(R.id.textViewExercise1)).setText(ExerciseData.names[exercises[0].ordinal()]);
                ((TextView)getView().findViewById(R.id.textViewExercise2)).setText(ExerciseData.names[exercises[1].ordinal()]);
                ((TextView)getView().findViewById(R.id.textViewExercise3)).setText(ExerciseData.names[exercises[2].ordinal()]);
                ((TextView)getView().findViewById(R.id.textViewExercise4)).setText(ExerciseData.names[exercises[3].ordinal()]);
                ((TextView)getView().findViewById(R.id.textViewExercise5)).setText(ExerciseData.names[exercises[4].ordinal()]);
            }
        });

        ImageButton[] rerolls = new ImageButton[] {
                rootView.findViewById(R.id.imageButtonReroll1),
                rootView.findViewById(R.id.imageButtonReroll2),
                rootView.findViewById(R.id.imageButtonReroll3),
                rootView.findViewById(R.id.imageButtonReroll4),
                rootView.findViewById(R.id.imageButtonReroll5),
        };
        for(int i = 0; i < rerolls.length; i++ ) {
            final int a = i; //haxi :D
            rerolls[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ((TextView)rootView.findViewById(R.id.textViewExercise1)).setText(ExerciseData.names[Global.getInstance().renewExercise(a).ordinal()]);
                }
            });
        }

        Button buttonSave = (Button) rootView.findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.getInstance().setRepetitions(new int[] {
                        Integer.valueOf(((EditText)rootView.findViewById(R.id.editTextReps1)).getText().toString()),
                        Integer.valueOf(((EditText)rootView.findViewById(R.id.editTextReps2)).getText().toString()),
                        Integer.valueOf(((EditText)rootView.findViewById(R.id.editTextReps3)).getText().toString()),
                        Integer.valueOf(((EditText)rootView.findViewById(R.id.editTextReps4)).getText().toString()),
                        Integer.valueOf(((EditText)rootView.findViewById(R.id.editTextReps5)).getText().toString()),
                });
            }
        });

        //return inflater.inflate(R.layout.fragment_first, container, false);
        return rootView;
    }
}
