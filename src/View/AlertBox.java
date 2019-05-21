package View;

import Controllers.FirstController;
import Menu.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import sun.font.TrueTypeFont;

public class AlertBox {
    static boolean i;

    public boolean display(int score,String highScore){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        BorderPane layout=new BorderPane();
        Image backgroundImage=new Image("Menu/Resources/alertbox_background.png",900 ,700,false,true);
        BackgroundImage background=new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        layout.setBackground(new Background(background));

        Label scoreMessage=new Label("Ｙｏｕｒ Ｓｃｏｒｅ ｉｓ "+score);
        scoreMessage.setFont(new Font(30));
        scoreMessage.setTextFill(Color.GREEN);
        scoreMessage.setTextAlignment(TextAlignment.CENTER);
        scoreMessage.setOnMouseEntered(e->{
            scoreMessage.setCursor(Cursor.TEXT);
        });
        Label highscoreMessage=new Label(""+highScore);
        highscoreMessage.setFont(new Font(20));
        highscoreMessage.setTextAlignment(TextAlignment.CENTER);
        highscoreMessage.setOnMouseEntered(e->{
            highscoreMessage.setCursor(Cursor.TEXT);
        });
        VBox labelsVbox=new VBox();
        labelsVbox.setAlignment(Pos.CENTER);
        labelsVbox.setSpacing(5);
        labelsVbox.getChildren().addAll(scoreMessage,highscoreMessage);
        layout.setTop(labelsVbox);


        Text text=new Text("\uD835\uDC9F\uD835\uDC5C \uD835\uDCB4\uD835\uDC5C\uD835\uDCCA \uD835\uDCB2\uD835\uDCB6\uD835\uDCC3\uD835\uDCC9 \uD835\uDCAF\uD835\uDC5C \uD835\uDC45\uD835\uDC52\uD835\uDCC8\uD835\uDCC9\uD835\uDCB6\uD835\uDCC7\uD835\uDCC9 ?");
        text.setFont(new Font(20));
        text.setOnMouseEntered(e->text.setCursor(Cursor.TEXT));
        layout.setCenter(text);

        Button yesButton=new Button("Yes");
        yesButton.setFont(new Font(15));
        yesButton.setStyle("-fx-background-color: \tf5f5dc");
        yesButton.setOnMousePressed(e-> yesButton.setEffect(new DropShadow()));
        yesButton.setOnMouseReleased(e->yesButton.setEffect(null));
        Button noButton=new Button("No");
        noButton.setFont(new Font(15));
        noButton.setStyle("-fx-background-color: \tf5f5dc");
        noButton.setOnMousePressed(e->noButton.setEffect(new DropShadow()));
        noButton.setOnMouseReleased(e->noButton.setEffect(null));
        yesButton.setOnAction(e->{
            i=true;
        });
        noButton.setOnAction(e->{
            i=false;
            window.close();
            IMenu Imenu =new ButtonsDecorator(new BackgroundDecorator(new MainMenu()));
            Imenu=new LogoDecorator(Imenu);
            Imenu=new AudioDecorator(Imenu);
            Imenu.createlayout();
        });

        yesButton.setOnMouseEntered(e->{
            yesButton.setEffect(new DropShadow());
        });

        yesButton.setOnMouseExited(e->{
            yesButton.setEffect(null);
        });
        noButton.setOnMouseEntered(e->{
            noButton.setEffect(new DropShadow());
        });

        noButton.setOnMouseExited(e->{
            noButton.setEffect(null);
        });

        HBox buttonsHbox=new HBox();
        buttonsHbox.setAlignment(Pos.CENTER);
        buttonsHbox.setSpacing(5);
        buttonsHbox.getChildren().addAll(yesButton,noButton);
        layout.setBottom(buttonsHbox);

        Scene scene=new Scene(layout,300,150);
        window.initStyle(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.show();
        return i;
    }
}

