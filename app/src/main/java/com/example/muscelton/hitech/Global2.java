package com.example.muscelton.hitech;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Global2 {

    public final Random rng;
    private int difficulty; //current
    private Exercise[] exercises; //current
    private int[] repetitions; //current
    private ArrayList<int[]> repetitionHistory; //Sisältää jokaiselle päivälle (ohjelman käynnistämisestä) listan kunkin harjoituksen toistoista

    private String startDate;
    private long dayCount; //equals repetitionHistory.size();
    private long dayCountPrevious; //on the last opening of app

    private static final Global2 singleton = new Global2(); //luokka luodaan ohjelman kännistyessä

    private Global2() {
        this.rng = new Random(1337);
        //default values, unless loaded from save
        this.difficulty = 0;
        this.exercises = new Exercise[5];
        this.renewExercises();
        this.repetitions = new int[ExerciseData.count];
        this.repetitionHistory = new ArrayList<>();
        this.repetitionHistory.add(new int[ExerciseData.count]);
        this.dayCount = 0;
        this.dayCountPrevious = 0;
        this.startDate = SaveManager.sdf.format(new Date(System.currentTimeMillis()));
    }

    //Pääsy tähän luokkaan
    public static Global2 getInstance() {
        return singleton;
    }

    public int getDifficulty() { return this.difficulty; }
    public Exercise[] getExercises() { return this.exercises; }
    public int[] getRepetitions() { return this.repetitions; }
    public ArrayList<int[]> getRepetitionHistory() {
        return this.repetitionHistory;
    }
    public long getDayCount() {return this.dayCount; }
    public String getStartDate() {return this.startDate; }
    public long getDayCountPrevious() {return this.dayCountPrevious; }

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
    public Exercise renewExercise(int index) {
        int randomIndex = rng.nextInt(6);
        this.exercises[index] = getExercisesByDifficulty()[randomIndex];
        return this.exercises[index];
    }

    private Exercise[] getExercisesByDifficulty() {
        return this.difficulty == 0 ? ExerciseData.easy : this.difficulty == 1 ? ExerciseData.medium : ExerciseData.hard;
    }

    public long validateLoad(String startDate, int dayCountPrevious){
       try {
           this.startDate = startDate;
           this.dayCountPrevious = dayCountPrevious;
           Date start = SaveManager.sdf.parse(startDate);
           Date now = new Date(System.currentTimeMillis());
           this.dayCount = TimeUnit.DAYS.convert(now.getTime() - start.getTime(), TimeUnit.MILLISECONDS);
           long daysPassed =  this.dayCount - this.dayCountPrevious;
           for(long i = 0; i < daysPassed - 1; i++) //Here we add all the gap days to history with zero reps.
               this.repetitionHistory.add(new int[ExerciseData.count]);
           if(daysPassed > 0) {
               this.repetitionHistory.add(this.repetitions); //save reps and star over
               this.repetitions = new int[ExerciseData.count];
           }
           return daysPassed;
       } catch (Exception e) {
           Log.d("lmao", "Date parse exception (save data corrupted)");
           return 0;
        }
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

