package com.example.muscelton.hitech;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SaveManager {

    public static final String historyFile = "muscleton_history.csv"; //data is appended through time
    public static final String saveFile = "muscleton_save.csv"; //data is appended through time

    /*data storage format:
    int difficulty; int[] exercises; int[] repetitions
     */
    public static void loadAllData(Context context) {
        Log.d("lmao", "-------------------------- BEGIN LOAD" );
        String saveData = readFile(context, saveFile); //daily
        if(saveData == null) return;

        String[] prefs = saveData.split(";");
        String[] exerciseStrings = prefs[1].split(",");
        String[] repetitionsStrings = prefs[2].split(",");

        Exercise[] exercises = new Exercise[exerciseStrings.length];
        int[] repetitions = new int[repetitionsStrings.length];
        for (int i = 0; i < exerciseStrings.length; i++)
            exercises[i] = Exercise.values()[Integer.valueOf(exerciseStrings[i])]; //String -> Exercise
        for (int i = 0; i < ExerciseData.count; i++)
            repetitions[i] = Integer.valueOf(repetitionsStrings[i]); //String -> int

        String historyData = readFile(context, historyFile);
        ArrayList<int[]> repetitionHistory = new ArrayList<>();
        String[] sets = historyData.split(";");
        for (String set : sets) {
            String[] values = set.split(",");
            int[] r = new int[ExerciseData.count];
            for (int i = 0; i < r.length; i++)
                r[i] = Integer.valueOf(values[i]);
            repetitionHistory.add(r);
        }

        Global2 g = Global2.getInstance();
        g.setDifficulty(Integer.valueOf(prefs[0]));
        g.setExercises(exercises);
        g.setRepetitions(repetitions);
        g.setRepetitionHistory(repetitionHistory);
        Log.d("lmao", "Loaded data: " + g.toString() );
    }

    public static void saveAllData(Context context) {
        Log.d("lmao", "-------------------------- BEGIN SAVE" );
        StringBuilder sb = new StringBuilder();
        Global2 g = Global2.getInstance();

        sb.append(g.getDifficulty()).append(";");
        for (Exercise e : g.getExercises())
            sb.append(e.ordinal()).append(",");
        sb.setLength(sb.length() - 1); //useless comma
        sb.append(";");
        for(int rep: g.getRepetitions())
            sb.append(Integer.valueOf(rep)).append(",");
        sb.setLength(sb.length() - 1); //useless comma

        writeFile(context, saveFile, sb.toString(), false);
        Log.d("lmao", "Saved savefile: " + sb.toString() );

        sb = new StringBuilder();
        for (int[] reps : g.getRepetitionHistory()) {
            for(int rep: reps)
                sb.append(rep).append(",");
            sb.setLength(sb.length() - 1);
            sb.append(";");
        }
        sb.setLength(sb.length() - 1); //useless semicolon
        writeFile(context, historyFile, sb.toString(), false);
        Log.d("lmao", "Saved history: " + sb.toString() );
    }

    public static void clearHistory() {

    }

    public static String readFile(Context context, String fileName) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            file.createNewFile();
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine(); //meidän tapauksessa luetaan vain yksi rivi
            if (line == null) return null; //ei dataa
            sb.append(line);
            reader.close();
            inputStream.close();
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Get rekt son: " + e.toString()); //for debug
        }
    }

    public static void writeFile(Context context, String fileName, String data, boolean append) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    context.openFileOutput(fileName, append ? Context.MODE_APPEND : Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Tiedoston " + fileName + " kirjoittaminen epäonnistui. Virhe: " + e.getMessage()); //for debug
        }
        Log.d("lmao", "Wrote to file: " + (new File(context.getFilesDir(), fileName).getAbsolutePath()));
    }

}
