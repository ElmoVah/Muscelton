package com.example.muscelton;

import java.util.ArrayList;
import java.util.List;

public class Global {
    private List<String> easy;
    private List<String> normal;
    private List<String> hard;
    private List<CharSequence> latestWorkout;

    private static final Global ourinstance = new Global();
    public static Global getInstance(){
        return ourinstance;
    }

    private Global(){
        this.easy = new ArrayList<>();
        this.easy.add("Knee push-up");
        this.easy.add("Elbow raise");
        this.easy.add("Scapular Push");
        this.easy.add("Squat");
        this.easy.add("Three way lunge");
        this.easy.add("Plank opposite knee to elbow");
        this.easy.add("Jumping jacks");
        this.easy.add("ISO crunches");
        this.easy.add("Recliner elbow to knee tucks");
        this.easy.add("Glute bridge");

        this.normal = new ArrayList<>();
        this.normal.add("Push-up");
        this.normal.add("Spiderman pushup");
        this.normal.add("Pike push-up");
        this.normal.add("Beginner Shrimp");
        this.normal.add("Floor bridge half rotation");
        this.normal.add("Supermans");
        this.normal.add("Starfish crunches");
        this.normal.add("Windshield wipers");
        this.normal.add("Heels to the heavens");
        this.normal.add("Burpee");
        this.normal.add("Split squat jumps");
        this.normal.add("Squat with a jump");
        this.normal.add("Single leg RDL");

        this.hard = new ArrayList<>();
        this.hard.add("Bodyweight side lateral raises");
        this.hard.add("Diamond push-up");
        this.hard.add("Plyo pushup");
        this.hard.add("Archer push-up");
        this.hard.add("Hindu push-up");
        this.hard.add("Squat thrust x push-up");
        this.hard.add("Bridge");
        this.hard.add("Coussac squat");
        this.hard.add("Burpee");
        this.hard.add("Starfish crunches");
        this.hard.add("Windshield wipers");
        this.hard.add("Heels to the heavens");

    }

    public void saveWokout(List<CharSequence> latestWorkout){
        this.latestWorkout = latestWorkout;
    }

    public List<CharSequence> getLatestWorkout(){
        return  this.latestWorkout;
    }

    public List<String> getWorkoutSheet(int i) {
        if (i == 1){
            return this.easy;
        } else if (i==2){
            return this.normal;
        } else {
            return this.hard;
        }
}
}
