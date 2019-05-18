package Menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenu  implements IMenu {

    protected AnchorPane layout;
    private Stage primaryStage;
    private Scene scene;


//    public MainMenu() {
//            createlayout();
//    }




    private void intializeStage(){
        layout=new AnchorPane();
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
    public AnchorPane getLayout() {
        return layout;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
