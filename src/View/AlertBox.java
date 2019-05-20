package View;

import Menu.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public void display(String title , String message1 , String message2 ,String message3){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(330);
        window.setMinHeight(100);

        Label messageLabel1 = new Label(message1);
        messageLabel1.setAlignment(Pos.CENTER);
        messageLabel1.setLayoutX(0);

        Label messageLabel2 = new Label(message2);
        messageLabel2.setAlignment(Pos.CENTER);
        messageLabel2.setLayoutY(20);
        messageLabel2.setLayoutX(0);

        Label messageLabel3 = new Label(message3);
        messageLabel3.setAlignment(Pos.CENTER);
        messageLabel3.setLayoutX(0);
        messageLabel3.setLayoutY(40);

        Button okButton = new Button("OK");


        okButton.setAlignment(Pos.CENTER);
        okButton.setLayoutX(105);
        okButton.setLayoutY(60);
        okButton.setPrefWidth(75);
        okButton.setOnAction(e->{
            window.close();
            IMenu Imenu =new ButtonsDecorator(new BackgroundDecorator(new MainMenu()));
            Imenu=new LogoDecorator(Imenu);
            Imenu=new AudioDecorator(Imenu);
            Imenu.createlayout();
        });

        AnchorPane layout  = new AnchorPane();
        layout.getChildren().addAll(messageLabel1,messageLabel2,messageLabel3,okButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

    }
}
