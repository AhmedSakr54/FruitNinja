package Menu;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ButtonsDecorator extends AddOnDecorator {


    public ButtonsDecorator(IMenu Imenudecorator) {
        super(Imenudecorator);
    }

    @Override
    public void createlayout() {
        super.createlayout();
        createButtons();
    }

    public void createButtons(){
        createNewGameButton();
        createLoadButton();
        createExitButton();
    }

    public void createNewGameButton(){
        Buttons newGameButton=new Buttons("New Game");
        addMenuButtons(newGameButton);
    }
    public void createLoadButton(){
        Buttons loadButton=new Buttons("Load");
        addMenuButtons(loadButton);
    }
    public void createExitButton(){
        Buttons exitButton=new Buttons("Exit");
        addMenuButtons(exitButton);
    }

    public void addMenuButtons(Buttons button){
        VBox Menu =new VBox();
        Menu.setAlignment(Pos.CENTER);
        Menu.getChildren().add(button);
        addMenu(Menu);
    }
    public void addMenu(VBox vBox){
        this.Imenudecorator.getLayout().setCenter(vBox);
    }
}
