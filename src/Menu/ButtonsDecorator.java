package Menu;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ButtonsDecorator extends AddOnDecorator {
    Buttons newGameButton;
    Buttons loadButton;
    Buttons exitButton;
    DifficultyMenu difficultyMenu=new DifficultyMenu();


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
        newGameButton.setOnAction(e-> {
            difficultyMenu.display();
        });
    }
    public void createLoadButton(){
        loadButton=new Buttons("Load");
    }
    public void createExitButton(){
        exitButton=new Buttons("Exit");
        exitButton.setOnAction(e->MainMenu.primaryStage.close());
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
