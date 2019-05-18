package gameModel;

import View.GameView;


public class Scores{
    private int numOfLives;
    private int gameScore;
    private GameView theView;

    public Scores(int numOfLives , int gameScore , GameView theView){
        this.numOfLives = numOfLives;
        this.gameScore = gameScore;
        this.theView = theView;
    }

    public int getNumOfLives() {
        return numOfLives;
    }

    public void setNumOfLives(int numOfLives) {
        this.numOfLives = numOfLives;
        notifyObservers(1);
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
        notifyObservers(2);
    }


    public void notifyObservers(int i){
        if(i == 1){
            theView.updateLives(this);
        }
        else
            theView.updateScores(this);


    }
}
