package Menu;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ButtonsDecorator extends AddOnDecorator {
    Buttons newGameButton;
    Buttons loadButton;
    Buttons exitButton;


    public ButtonsDecorator(IMenu Imenudecorator) {
        super(Imenudecorator);
    }

    @Override
    public void createlayout() {
        super.createlayout();
        createButtons();
        addMenuButtons();
    }

    public void createButtons(){
        createNewGameButton();
        createLoadButton();
        createExitButton();
    }

    public void createNewGameButton(){
        newGameButton=new Buttons("New Game");
    }
    public void createLoadButton(){
        loadButton=new Buttons("Load");
    }
    public void createExitButton(){
        exitButton=new Buttons("Exit");
    }

    public void addMenuButtons(){
        VBox Menu =new VBox();
        Menu.setAlignment(Pos.CENTER);
        Menu.setSpacing(50);
        Menu.getChildren().addAll(newGameButton,loadButton,exitButton);
        addMenu(Menu);
    }
    public void addMenu(VBox vBox){
        this.Imenudecorator.getLayout().setCenter(vBox);
    }
}
