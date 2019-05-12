package View;

import Controllers.*;
import gameModel.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<ThrowableObject> randomObjects = new ArrayList<>();
        GameView view = new GameView();
        GameActions controller = new FirstController(view,new NormalStrategy());
    }
}
