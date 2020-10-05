package com.example.muscelton.fragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.muscelton.DisplayWorkoutdata;
import com.example.muscelton.MainActivity;
import com.example.muscelton.R;
import com.example.muscelton.hitech.Global;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second extends Fragment {

    private View secondView;
    public static String EXTRA = "vähänlisätekstiä";

    public Second() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       secondView = inflater.inflate(R.layout.fragment_second, container, false);

        ListView dates = secondView.findViewById(R.id.ListViewDates);
        dates.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Global.getInstance().getDayCount()));

        dates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent nextActivity = new Intent(getActivity(), DisplayWorkoutdata.class);
                nextActivity.putExtra(EXTRA, i);
                startActivity(nextActivity);
            }
        });


        return secondView;
    }

}
