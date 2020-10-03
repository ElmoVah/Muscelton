package com.example.muscelton;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muscelton.hitech.Exercise;
import com.example.muscelton.hitech.ExerciseData;
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

}
