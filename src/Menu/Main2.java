package Menu;

import Menu.BackgroundDecorator;
import Menu.ButtonsDecorator;
import Menu.IMenu;
import Menu.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main2 extends Application{


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        IMenu Imenu =new ButtonsDecorator(new BackgroundDecorator(new MainMenu()));
        Imenu.createlayout();
    }
}
