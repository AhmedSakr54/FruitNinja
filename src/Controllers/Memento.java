package Controllers;

import gameModel.ThrowableObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Memento {
    private int numOflives;
    private int seconds;
    private int minutes;
    private int scores;
    private ArrayList<ThrowableObject> objects ;

    public Memento(int numOflives , int seconds , int minutes , int scores, ArrayList<ThrowableObject> objects){
        this.numOflives = numOflives;
        this.seconds = seconds;
        this.minutes = minutes;
        this.scores = scores;
    }

    public int getNumOflives() {
        return numOflives;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }
    public int getScores(){
        return scores;
    }

    public ArrayList<ThrowableObject> getObjects() {
        return objects;
    }
}
