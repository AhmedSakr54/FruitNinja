package View;

import Controllers.FirstController;
import gameModel.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.shape.Path;

public class GameView{

    private AnchorPane pane;
    private Stage primaryStage;
    private Scene scene;
    private Path path;
    private Label livesLabel;
    private Label scoreLabel;
    private Label lives;
    private Label score;
    private Label minLabel , secLabel , separator , highScoreLabel;
    private Button resetBtn;

    public AnchorPane getPane() {
        return pane;
    }
    public Stage getPrimaryStage(){
        return this.primaryStage;
    }
    public Path getPath(){
        return this.path;
    }
    public Scene getScene(){return this.scene;}
    public void setResetBtn(Button btn){
        this.resetBtn = btn;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }
    public Button getResetBtn(){
        return this.resetBtn;
    }
    public GameView(){
        intializeStage();
        createBackground();
        createLabels();
        createButton();
    }
    private void intializeStage(){
        primaryStage = new Stage();
        path = new Path();
        path.setStrokeWidth(2);

        path.setStroke(Color.WHITE);
        pane = new AnchorPane();
        pane.getChildren().addAll(path);
        scene = new Scene(pane,950,900);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e->{
            System.exit(0);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void createButton(){
        resetBtn = new Button("RESET");
        resetBtn.setLayoutX(500);
        pane.getChildren().addAll(resetBtn);
    }
    private void createLabels(){
        Label time = new Label("Timer");
        highScoreLabel = new Label("0");
        minLabel = new Label("0");
        secLabel = new Label("0");
        separator = new Label(" : ");
        livesLabel = new Label("3");
        scoreLabel = new Label("0");
        lives = new Label("LIVES:");
        score = new Label("SCORE:");


        lives.setFont(new Font(50));
        score.setFont(new Font(50));
        livesLabel.setFont(new Font(50));
        highScoreLabel.setFont(new Font(50));
        scoreLabel.setFont(new Font(50));
        minLabel.setFont(new Font(50));
        secLabel.setFont(new Font(50));
        separator.setFont(new Font(50));
        time.setFont(new Font(50));

        highScoreLabel.setLayoutX(100);
        highScoreLabel.setLayoutY(100);
        secLabel.setLayoutX(900);
        secLabel.setLayoutY(70);
        separator.setLayoutX(860);
        separator.setLayoutY(70);
        minLabel.setLayoutY(70);
        minLabel.setLayoutX(840);
        time.setLayoutX(780);
        lives.setLayoutX(0);
        livesLabel.setLayoutX(150);
        score.setLayoutY(50);
        scoreLabel.setLayoutY(50);
        scoreLabel.setLayoutX(180);


        lives.setTextFill(Color.WHITE);
        score.setTextFill(Color.WHITE);
        minLabel.setTextFill(Color.WHITE);
        secLabel.setTextFill(Color.WHITE);
        separator.setTextFill(Color.WHITE);
        scoreLabel.setTextFill(Color.WHITE);
        livesLabel.setTextFill(Color.WHITE);
        time.setTextFill(Color.WHITE);
        highScoreLabel.setTextFill(Color.WHITE);
        pane.getChildren().addAll(livesLabel,scoreLabel,score,lives,minLabel,secLabel,separator,time,highScoreLabel);
    }
    private void createBackground(){
        Image backgroundImage=new Image("View/Resources/BackGrounds/background.jpg",900,900,false,true);
        BackgroundImage background=new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        pane.setBackground(new Background(background));
    }

    public void showSwiping(EventHandler mouseHandler){
        scene.setOnMouseClicked(mouseHandler);
        scene.setOnMouseDragged(mouseHandler);
        scene.setOnMouseEntered(mouseHandler);
        scene.setOnMouseExited(mouseHandler);
        scene.setOnMouseMoved(mouseHandler);
        scene.setOnMousePressed(mouseHandler);
        scene.setOnMouseReleased(mouseHandler);
    }


    public void updateScores(Scores o) {
            scoreLabel.setText(Integer.toString(o.getGameScore()));
    }
    public void updateLives(Scores o){
        livesLabel.setText(Integer.toString(o.getNumOfLives()));
    }

    public Label getMinLabel() {
        return minLabel;
    }

    public void setMinLabel(Label minLabel) {
        this.minLabel = minLabel;
    }

    public Label getSecLabel() {
        return secLabel;
    }

    public void setSecLabel(Label secLabel) {
        this.secLabel = secLabel;
    }

    public Label getHighScoreLabel() {
        return highScoreLabel;
    }

    public void setHighScoreLabel(Label highScoreLabel) {
        this.highScoreLabel = highScoreLabel;
    }
}
