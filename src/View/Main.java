package View;

import Controllers.*;
import Menu.BackgroundDecorator;
import Menu.IMenu;
import Menu.MainMenu;
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
        //IMenu Imenu=new MainMenu();
        //MainMenu mainMenu=new MainMenu();
//        IMenu Imenu =new BackgroundDecorator(new MainMenu());
//        Imenu.createlayout();
        GameActions controller = new FirstController(view,new EasyStrategy());
    }
}
