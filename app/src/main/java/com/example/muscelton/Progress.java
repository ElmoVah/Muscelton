package com.example.muscelton;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muscelton.hitech.Global2;
import com.example.muscelton.hitech.SaveManager;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Progress extends Fragment { //aka "Second" :D


    public Progress() {
        // Required empty public constructor1cDESWXZ
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    private void ProHiTechVinkkei() {
        //Pro vinkkejä
        ArrayList<int[]> toistoHistoria = Global2.getInstance().getRepetitionHistory();

        //Esimerkki: ekan päivän toistomäärä harjotukselle 0, eli harjotukselle "knee push up"
        int a = toistoHistoria.get(0)[0];

        //tälleen ne voi käydä kaikki läpi
        int toistojaYht = 0;
        for(int[] toistot: toistoHistoria) {
            for (int toistoa : toistot) {
                toistojaYht += toistoa;
            }
        }
    }

}
