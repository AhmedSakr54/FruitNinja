package Menu;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BackgroundDecorator extends AddOnDecorator {



    public BackgroundDecorator(IMenu Imenudecorator) {
        super(Imenudecorator);
    }


    @Override
    public void createlayout() {
        Imenudecorator.createlayout();
        createBackGround();
    }


    private void createBackGround(){
        Image backgroundImage;
        backgroundImage=new Image("Menu/Open_Background_11.jpg",900 ,700,false,true);
        BackgroundImage background=new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        this.Imenudecorator.getLayout().setBackground(new Background(background));
    }

}
