package Menu;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Buttons extends Button {
    private static final String FONT_PATH = "src/normalFont.ttf";
    private static final String BUTTON_RELEASED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('Menu/yellow_button00.png');";
    private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('Menu/yellow_button01.png');";

    public Buttons(String text){
        setText(text);
        setButtonFont();
        setPrefHeight(49);
        setPrefWidth(190);
        setStyle(BUTTON_RELEASED_STYLE);
        initializeButtonListeners();
    }

    public void setButtonFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH) , 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana" , 23));
        }

    }

    private void initializeButtonListeners(){
        setOnMousePressed(e->{
            if(e.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });
        setOnMouseReleased(e->{
            if(e.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });

        setOnMouseEntered(e->{
            setEffect(new DropShadow());
        });

        setOnMouseExited(e->{
            setEffect(null);
        });
    }
    private void setButtonPressedStyle(){
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }
    private void setButtonReleasedStyle(){
        setStyle(BUTTON_RELEASED_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }
}
