package com.example.muscelton.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
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
    private TextView[] eTextViews;
    private ImageButton[] rerolls;
    private EditText[] editTexts;

    public First() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false);
        rootView = inflater.inflate(R.layout.fragment_first, container, false);

        eTextViews = new TextView[]{
                rootView.findViewById(R.id.textViewExercise1),
                rootView.findViewById(R.id.textViewExercise2),
                rootView.findViewById(R.id.textViewExercise3),
                rootView.findViewById(R.id.textViewExercise4),
                rootView.findViewById(R.id.textViewExercise5),
        };
        rerolls = new ImageButton[] {
                rootView.findViewById(R.id.imageButtonReroll1),
                rootView.findViewById(R.id.imageButtonReroll2),
                rootView.findViewById(R.id.imageButtonReroll3),
                rootView.findViewById(R.id.imageButtonReroll4),
                rootView.findViewById(R.id.imageButtonReroll5),
        };
        editTexts = new EditText[] {
                rootView.findViewById(R.id.editTextReps1),
                rootView.findViewById(R.id.editTextReps2),
                rootView.findViewById(R.id.editTextReps3),
                rootView.findViewById(R.id.editTextReps4),
                rootView.findViewById(R.id.editTextReps5),
        };

        UpdateUI(false);

        ((RadioGroup) rootView.findViewById(R.id.radioGroupDifficulty)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Global.getInstance().setDifficulty(i == R.id.radioButtonEasy ? 0 : i == R.id.radioButtonNormal ? 1 : 2);
                UpdateUI(true);
            }
        });

        ((Button) rootView.findViewById(R.id.buttonUnlockDifficulty)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wasEnabled = rootView.findViewById(R.id.radioButtonEasy).isEnabled();
                for(int id: new int[] {R.id.radioButtonEasy, R.id.radioButtonNormal, R.id.radioButtonHard})
                    rootView.findViewById(id).setEnabled(!wasEnabled);
                ((Button) rootView.findViewById(R.id.buttonUnlockDifficulty)).setText(wasEnabled ? "Avaa" : "Lukitse");
            }
        });

        for(int i = 0; i < rerolls.length; i++ ) {
            final int a = i; //haxi :D
            rerolls[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Exercise e = Global.getInstance().renewExercise(a);
                    UpdateUI(false);
                }
            });
        }

        for(int i = 0; i < editTexts.length; i++) {
            final int a = i;
            editTexts[i].addTextChangedListener(new TextWatcher() {

                public void onTextChanged(CharSequence c, int start, int before, int count) {
                    int rep = c.length() == 0 || c.length() > 2 ? 0 : Integer.parseInt(c.toString());
                    Global.getInstance().getRepetitions()[Global.getInstance().getExercises()[a].ordinal()] = c.length() == 0 || c.length() > 2 ? 0 : Integer.parseInt(c.toString());
                }
                public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                    ((Button) rootView.findViewById(R.id.buttonRerollAll)).setEnabled(false);
                }
                public void afterTextChanged(Editable c) {
                    ((Button) rootView.findViewById(R.id.buttonRerollAll)).setEnabled(true);
                }
            });
        }
        ((Button) rootView.findViewById(R.id.buttonRerollAll)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateUI(true);
            }
        });
        return rootView;

    }
    private void UpdateUI(boolean renew) {
        Exercise[] exercises = renew ? Global.getInstance().renewExercises() : Global.getInstance().getExercises();
        int[] reps = Global.getInstance().getRepetitions();
        for(int j = 0; j < this.eTextViews.length; j++) {
            int id = exercises[j].ordinal();
            eTextViews[j].setText(ExerciseData.names[id]);
            editTexts[j].setText(reps[id] != 0 ? String.valueOf(reps[id]) : "");
        }
    }
}
