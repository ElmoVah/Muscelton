package com.example.muscelton;

import android.util.Log;

import com.example.muscelton.hitech.Exercise;
import com.example.muscelton.hitech.ExerciseData;
import com.example.muscelton.hitech.Global2;

import java.util.ArrayList;

public class HiTechTutoriaali {
    private HiTechTutoriaali() {


        Exercise[] exercises = Global2.getInstance().getExercises(); //Tän hetkiset 5 harjoitusta
        for(Exercise e: exercises)
            Log.d("lmao","Muuttujan nimi: " + e.name()); //KNEE_PUSH_UP
        for(Exercise e: exercises)
            Log.d("lmao","Indeksi: " + e.ordinal()); //0
        for(Exercise e: exercises)
            Log.d("lmao", "Nimi: " + ExerciseData.names[e.ordinal()]); //Knee push up

        //Toistohistoria
        ArrayList<int[]> toistoHistoria = Global2.getInstance().getRepetitionHistory();

        int a = toistoHistoria.get(0)[0]; //päivän 1 toistomäärä harjotukselle jonka indeksi on 0, eli harjotukselle "knee push up"
        int b = toistoHistoria.get(4)[16]; //päivän 5 toistomäärä harjotukselle jonka indeksi on 16, eli harjotukselle "SUPERMANS"

        //koko historian läpi käynti
        int toistojaYht = 0;
        for(int[] toistot: toistoHistoria) {
            for (int toistoa : toistot) {
                toistojaYht += toistoa;
            }
        }
    }
}
