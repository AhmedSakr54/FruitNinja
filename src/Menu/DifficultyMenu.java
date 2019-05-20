package Menu;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DifficultyMenu {
    public void display(){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        BorderPane layout=new BorderPane();
        Image backgroundImage=new Image("Menu/Resources/Gold.png",900 ,700,false,true);
        BackgroundImage background=new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        layout.setBackground(new Background(background));

        Text difficultyText=new Text("       ℂ\uD835\uDD59\uD835\uDD60\uD835\uDD60\uD835\uDD64\uD835\uDD56 \uD835\uDD3B\uD835\uDD5A\uD835\uDD57\uD835\uDD57\uD835\uDD5A\uD835\uDD54\uD835\uDD66\uD835\uDD5D\uD835\uDD65\uD835\uDD6A");
        difficultyText.setFont(new Font(50));
        StackPane difficultyTextPane=new StackPane();
        difficultyTextPane.setAlignment(Pos.CENTER);
        layout.setTop(difficultyText);

        Buttons easyButton=new Buttons("Easy");
        Buttons normalButton=new Buttons("Normal");
        Buttons hardButton=new Buttons("Hard");
        VBox buttonsVbox=new VBox();
        buttonsVbox.setAlignment(Pos.CENTER);
        buttonsVbox.setSpacing(30);
        buttonsVbox.getChildren().addAll(easyButton,normalButton,hardButton);
        layout.setCenter(buttonsVbox);

        Text returnText=new Text(" ░R░e░t░u░r░n░ ░t░o░ ░M░e░n░u░ ");
        returnText.setFont(new Font(30));
        returnText.setOnMouseEntered(e->{
            returnText.setEffect(new DropShadow());
            returnText.setFill(Color.BLUE);
            returnText.setCursor(Cursor.HAND);
        });

        returnText.setOnMouseExited(e->{
            returnText.setEffect(null);
            returnText.setFill(Color.BLACK);
        });
        returnText.setOnMouseClicked(e->{
            window.close();
        });
        layout.setBottom(returnText);

        Scene scene=new Scene(layout);
        window.initStyle(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.showAndWait();
    }
}
