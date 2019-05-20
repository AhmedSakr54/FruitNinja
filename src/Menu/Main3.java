package Menu;

import Menu.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main3 extends Application{


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        IMenu Imenu =new ButtonsDecorator(new BackgroundDecorator(new MainMenu()));
        Imenu=new LogoDecorator(Imenu);
        Imenu=new AudioDecorator(Imenu);
        Imenu.createlayout();
    }
}
