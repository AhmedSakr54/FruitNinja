package View;

import Controllers.*;
import Menu.*;
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
        //GameView view = new GameView();
        //IMenu Imenu=new MainMenu();
        //MainMenu mainMenu=new MainMenu();
        //IMenu Imenu = new ButtonsDecorator(new BackgroundDecorator(new MainMenu()));

        //Imenu.createlayout();
        //AlertBox alert = new AlertBox();
        //alert.display("Game Over" , "You lost,But you beat your highScore");
        //GameActions controller = new FirstController(view,new HardStrategy());

        IMenu Imenu =new ButtonsDecorator(new BackgroundDecorator(new MainMenu()));
        Imenu=new LogoDecorator(Imenu);
        Imenu=new AudioDecorator(Imenu);
        Imenu.createlayout();
    }
}
