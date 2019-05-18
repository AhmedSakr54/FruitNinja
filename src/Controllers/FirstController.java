package Controllers;

import View.GameView;
import gameModel.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class FirstController implements GameActions {

    private List<ThrowableObject> randomObjects ;
    private ICommand command;
    private GameView theView;
    private ILevelDifficutlyStrategy difficultyStrategy;
    private AnimationTimer gameTimer;
    private int bombIntensity;
    private int throwablesIntensity = 0;
    private int seconds = 0;
    private Scores gameScores;
    private int buffer = 0;
    private SlicingAdapter adapter;
    private CareTaker careTaker;
    private Originator originator;
    private MouseEvent event1 ;
    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if(seconds < 59){
                seconds++;
            }
            else seconds = 0;

        }
    };


    public void initTimer(){
        timer.scheduleAtFixedRate(task,1000,1000);
    }
    public void stopTimer(){
        task.cancel();
        timer.cancel();
    }

    //Draws a line when mouse is dragged but still can't make the line cut the fruit
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            event1 = event;
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                theView.getPath().getElements().clear();
                theView.getPath().getElements().add(new MoveTo(event.getX(), event.getY()));

            } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                theView.getPath().getElements().add(new LineTo(event.getX(), event.getY()));
            }else if (event.getEventType() == MouseEvent.MOUSE_RELEASED){
                theView.getPath().getElements().clear();

            }
        }
    };

    public FirstController(GameView theView , ILevelDifficutlyStrategy difficutlyStrategy){
        this.randomObjects = new ArrayList<>();
        this.theView = theView;
        this.theView.showSwiping(mouseHandler);
        this.theView.getResetBtn().setOnAction(e->{
            resetGame();
        });
        this.gameScores = new Scores(3,0,this.theView);
        this.difficultyStrategy = difficutlyStrategy;
        createGameLoop();
        initTimer();
    }

    public void setCommand(ICommand command){
        this.command = command;
    }


    public void putInThrowableObjects(){

        ThrowableObject randomObjects = (ThrowableObject) createGameObject();
        randomObjects.setyLocation(910 + difficultyStrategy.getDifficutly()*throwablesIntensity);
        randomObjects.getImageView()[0].setLayoutX(randomObjects.getXLocation());
        theView.getPane().getChildren().addAll(randomObjects.getImageView()[0]);
        setCommand(new SliceCommand(randomObjects));
        this.command.execute();

        this.randomObjects.add(randomObjects);
        this.throwablesIntensity++;


    }

    public void createGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(buffer <= 5 ) {
                    putInThrowableObjects();
                    updateObjectsLocation();
                    sliceObjects();
                    if (gameScores.getNumOfLives() == 0) {
                        gameTimer.stop();
                        stopTimer();
                        theView.getPrimaryStage().close();
                        System.out.println("Your Score : " + gameScores.getGameScore());
                    }
                    buffer++;
                }
                else buffer = 0;
                theView.getSecLabel().setText(Integer.toString(seconds));
                if(Integer.parseInt(theView.getSecLabel().getText()) == 59){
                    theView.getSecLabel().setText("0");
                    seconds = 0;
                    int min = Integer.parseInt(theView.getMinLabel().getText());
                    theView.getMinLabel().setText(Integer.toString(min));
                    theView.getMinLabel().setText(Integer.toString(min+1));
                }
            }
        };
        gameTimer.start();
    }

    @Override
    public GameObject createGameObject() {
        GameObject random = new ThrowableObject();
        ((ThrowableObject) random).setType(ObjectType.randomObject());
        if(random.getObjectType() == ObjectType.FATALBOMB || random.getObjectType() == ObjectType.DAMAGEBOMB){
            bombIntensity++;
        }
        if(bombIntensity > difficultyStrategy.getBombIntensityCap()){
            ((ThrowableObject) random).setType(ObjectType.randomObject());
            bombIntensity = 0;
        }

        return random;
    }

    @Override
    public void updateObjectsLocation() {
        Random random = new Random();

        for(int i = 0 ; i < randomObjects.size() ; i++){

            //checks if the ThrowableObject has reached the maximumHeight
            if(randomObjects.get(i).getYLocation() > randomObjects.get(i).getMaxHeight() && !randomObjects.get(i).isReachedMaxHeight()) {
                randomObjects.get(i).setyLocation(randomObjects.get(i).getYLocation() - difficultyStrategy.getFruitSpeed());
                double y = (double) randomObjects.get(i).getYLocation();
                randomObjects.get(i).getImageView()[0].setLayoutY(y);

            }
            //makes the Object fall back down from where it came from
            else{
                randomObjects.get(i).setReachedMaxHeight(true);
                randomObjects.get(i).setyLocation(randomObjects.get(i).getYLocation() + difficultyStrategy.getFruitSpeed());
                double y = (double) randomObjects.get(i).getYLocation();
                randomObjects.get(i).getImageView()[0].setLayoutY(y);
            }

            //checks if the Objects has got off Screen without being sliced
            if(randomObjects.get(i).isReachedMaxHeight()&&randomObjects.get(i).getYLocation() > 910){
                if(!randomObjects.get(i).isSliced()) {
                    randomObjects.get(i).setMovedOffScreen(true);

                }
            }
            //shows the movement of the sliced fruits
            if(randomObjects.get(i).isSliced() && randomObjects.get(i).getObjectType() != ObjectType.DAMAGEBOMB){
                setCommand( new MoveCommand(randomObjects.get(i)));
                this.command.execute();
            }
            //A decrease in the players life points when a fruit that has not been sliced, fell off screen
            if(randomObjects.get(i).hasMovedOffScreen() && randomObjects.get(i).getObjectType() != ObjectType.FATALBOMB && randomObjects.get(i).getObjectType() != ObjectType.DAMAGEBOMB){
                gameScores.setNumOfLives(gameScores.getNumOfLives() -1);
                theView.getPane().getChildren().remove(randomObjects.get(i).getImageView()[0]);
                randomObjects.remove(i);

            }
            //A decrease in the players life points when they slice the blue bombs
            if(randomObjects.get(i).isSliced() && randomObjects.get(i).getObjectType() == ObjectType.DAMAGEBOMB){
                gameScores.setNumOfLives(gameScores.getNumOfLives() -1);
                theView.getPane().getChildren().remove(randomObjects.get(i).getImageView()[0]);
                randomObjects.remove(i);
            }
        }
    }

    @Override
    public void sliceObjects() {
        for(int i = 0 ; i < randomObjects.size() ; i++){
            if(randomObjects.get(i).isSliced()){

                theView.getPane().getChildren().remove(randomObjects.get(i).getImageView()[0]);
                if(randomObjects.get(i).getObjectType() != ObjectType.FATALBOMB && randomObjects.get(i).getObjectType() != ObjectType.DAMAGEBOMB) {
                    gameScores.setGameScore(gameScores.getGameScore()+1);
                    randomObjects.get(i).getImageView()[1].setLayoutX(randomObjects.get(i).getXLocation() - 20);
                    randomObjects.get(i).getImageView()[2].setLayoutX(randomObjects.get(i).getXLocation() + 20);
                    randomObjects.get(i).getImageView()[1].setLayoutY(randomObjects.get(i).getYLocation());
                    randomObjects.get(i).getImageView()[2].setLayoutY(randomObjects.get(i).getYLocation());
                    theView.getPane().getChildren().addAll(randomObjects.get(i).getImageView()[1], randomObjects.get(i).getImageView()[2]);
                    randomObjects.remove(randomObjects.get(i));
                }
                //The players loses instantly when they slice the red bomb
                else {
                    gameTimer.stop();
                    stopTimer();
                    theView.getPrimaryStage().close();
                    //Implement a GAMEOVER screen to display the game score of the player
                    System.out.println("you lose");
                    System.out.println("Your Score : " + gameScores.getGameScore());
                }
            }
        }
    }

    @Override
    public void saveGame() {
        originator = new Originator();
        careTaker = new CareTaker();
        ArrayList<ThrowableObject> objects = new ArrayList<>();
        for(int i = 0 ; i < this.randomObjects.size() ; i++){
            if(this.randomObjects.get(i).getYLocation() < 900 && !this.randomObjects.get(i).isSliced()){
                objects.add(this.randomObjects.get(i));
            }
        }
        originator.setObjects(objects);
        originator.setNumOflives(gameScores.getNumOfLives());
        originator.setScores(gameScores.getGameScore());
        originator.setSeconds(seconds);
        originator.setMinutes(Integer.parseInt(theView.getMinLabel().getText()));
        careTaker.addMemento(originator.save());

        try{
            FileOutputStream fos = new FileOutputStream(new File("saves.xml"));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(careTaker.getMemento());
            encoder.close();
            fos.close();
        }catch (IOException e1){
            e1.printStackTrace();
        }

    }

    @Override
    public void loadGame() {
        try{
            FileInputStream fis = new FileInputStream(new File("saves.xml"));
            XMLDecoder decoder = new XMLDecoder(fis);
            this.originator = (Originator) decoder.readObject();
            decoder.close();
            fis.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void resetGame() {
        new FirstController(new GameView() , this.difficultyStrategy);
        theView.getPrimaryStage().close();
        gameTimer.stop();
        timer.cancel();
    }

}
