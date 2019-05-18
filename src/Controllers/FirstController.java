package Controllers;

import View.GameView;
import gameModel.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

import java.io.*;
import java.util.*;


public class FirstController implements GameActions {

    private List<ThrowableObject> randomObjects ;
    private ICommand command;
    private GameView theView;
    private int difficultyIndex;
    private ILevelDifficutlyStrategy difficultyStrategy;
    private AnimationTimer gameTimer;
    private int bombIntensity;
    private int throwablesIntensity = 0;
    private int seconds = 0;
    private ArrayList<String> highScores = new ArrayList<>();
    private Scores gameScores;
    private int buffer = 0;
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
        loadHighScore();
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
                        saveHighScore();
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
                    saveHighScore();
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
    }

    @Override
    public void loadGame() {
    }

    @Override
    public void resetGame() {
        new FirstController(new GameView() , this.difficultyStrategy);
        theView.getPrimaryStage().close();
        gameTimer.stop();
        timer.cancel();
    }

    public boolean max(){
        if(this.gameScores.getGameScore() > Integer.parseInt(this.theView.getHighScoreLabel().getText())){
            //TODO close the game with a message saying you beat your old high score
            return true;
        }
        return false;
    }

    public void saveHighScore(){
        if(max()){
            highScores.set(difficultyIndex,Integer.toString(this.gameScores.getGameScore()));
            File file = new File("HighScore");
            try {
                FileWriter filewriter = new FileWriter(file);
                for(int i = 0 ; i < highScores.size() ; i++){

                    filewriter.write(highScores.get(i));
                    if(i == highScores.size() - 1)
                        break;
                    filewriter.write("\n");
                }
                filewriter.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadHighScore(){
        File file = new File("HighScore");
        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()){
                String line = bufferedReader.readLine();
                highScores.add(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        if(difficultyStrategy instanceof EasyStrategy){
            theView.getHighScoreLabel().setText(highScores.get(0));
            difficultyIndex = 0;
        }
        else if(difficultyStrategy instanceof NormalStrategy){
            theView.getHighScoreLabel().setText(highScores.get(1));
            difficultyIndex = 1;
        }
        else {
            theView.getHighScoreLabel().setText(highScores.get(2));
            difficultyIndex = 2;
        }
    }

}
