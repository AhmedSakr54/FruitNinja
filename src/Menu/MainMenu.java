package Menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenu  implements IMenu {

    protected BorderPane layout;
    private Stage primaryStage;
    private Scene scene;






    private void intializeStage(){
        layout=new BorderPane();
        scene=new Scene(layout,900,700);
        primaryStage=new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fruit Ninja");
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

    public Stage getPrimaryStage() {
        return primaryStage;
    }


}