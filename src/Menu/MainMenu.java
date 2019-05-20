package Menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainMenu  implements IMenu {

    protected BorderPane layout;
    protected static Stage primaryStage=new Stage();
    private Scene scene;






    private void intializeStage(){
        layout=new BorderPane();
        scene=new Scene(layout,900,700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fruit Ninja");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void createlayout() {
        intializeStage();
    }

    @Override
    public BorderPane getLayout() {
        return layout;
    }




}