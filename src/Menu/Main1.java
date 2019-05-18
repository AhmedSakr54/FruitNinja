package Menu;

import Menu.BackgroundDecorator;
import Menu.IMenu;
import Menu.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main1 extends Application{


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //IMenu Imenu=new MainMenu();
        //MainMenu mainMenu=new MainMenu();
        IMenu Imenu =new BackgroundDecorator(new MainMenu());
        Imenu.createlayout();
        //Imenu.createlayout();
    }
}
