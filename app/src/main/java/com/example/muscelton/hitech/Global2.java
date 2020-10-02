package com.example.muscelton.hitech;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Global2 {

    public final Random rng;
    private int difficulty;
    private Exercise[] exercises;
    private int[] repetitions;
    private ArrayList<int[]> repetitionHistory; //Sisältää jokaiselle päivälle (ohjelman käynnistämisestä) listan kunkin harjoituksen toistoista

    private static final Global2 singleton = new Global2(); //luokka luodaan ohjelman kännistyessä

    private Global2() {
        rng = new Random(1337);
        //default values, unless loaded from save
        difficulty = 0;
        exercises = new Exercise[5];
        renewExercises();
        repetitions = new int[ExerciseData.count];
        repetitionHistory = new ArrayList<>();
        repetitionHistory.add(new int[ExerciseData.count]);

    }

    //Pääsy tähän luokkaan
    public static Global2 getInstance() {
        return singleton;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public Exercise[] getExercises() {
        return exercises;
    }

    public int[] getRepetitions() {
        return repetitions;
    }

    public ArrayList<int[]> getRepetitionHistory() {
        return repetitionHistory;
    }

    //set on startup.
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setExercises(Exercise[] exercises) {
        this.exercises = exercises;
    }

    public void setRepetitions(int[] repetitions) {
        this.repetitions = repetitions;
    }

    public void setRepetitionHistory(ArrayList<int[]> rh) {
        this.repetitionHistory = rh;
    }

    //Sufflaa uudet harjoitukset. Koska haluamme käyttää Elmon suositusta, Shufflea, pitää tehdä pari tyyppimuunnosta :(
    public Exercise[] renewExercises() {
        List<Exercise> newExercises = Arrays.asList(getExercisesByDifficulty()); //Exercise[] to List<Exercise>
        Collections.shuffle(newExercises);  //SHUFFLE!
        for(int i = 0; i < this.exercises.length; i++)
            exercises[i] = newExercises.get(i);
        return this.exercises; //List<Exercise> to Exercise[]
    }

    //Arpoo yhden uuden harjoituksen.
    public Exercise[] renewExercise(int index) {
        int harjoitus = rng.nextInt(6);
        this.exercises[index] = getExercisesByDifficulty()[harjoitus];
        return this.exercises;
    }

    private Exercise[] getExercisesByDifficulty() {
        return this.difficulty == 0 ? ExerciseData.easy : this.difficulty == 1 ? ExerciseData.medium : ExerciseData.hard;
    }

    @Override
    public String toString() {
        String exercisesString = "Exercises: ";
        for(Exercise e: exercises)
            exercisesString += e.name() + ", ";
        return "\nDifficulty: " + this.difficulty
                + "\n" + exercisesString
                + "\nRepetitions: " + Arrays.toString(repetitions)
                + "\nHistory days count: " + repetitionHistory.size();
    }
}

