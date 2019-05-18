package Controllers;

import gameModel.ThrowableObject;

import java.util.ArrayList;

public class Originator {
    private int numOflives;
    private int seconds;
    private int minutes;
    private int scores;
    private ArrayList<ThrowableObject> objects ;

    public void setNumOflives(int numOflives) {
        this.numOflives = numOflives;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setObjects(ArrayList<ThrowableObject> objects) {
        this.objects = objects;
    }

    public void setScores(int scores){
        this.scores = scores;
    }

    public Memento save(){
        return new Memento(numOflives,seconds,minutes,scores,objects);
    }
    public void load(Memento m){
        this.numOflives = m.getNumOflives();
        this.minutes = m.getMinutes();
        this.seconds = m.getSeconds();
        this.scores = m.getScores();
        this.objects = m.getObjects();
    }
}
