package Menu;


import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

public abstract class AddOnDecorator implements IMenu {
    protected IMenu Imenudecorator;

    public AddOnDecorator(IMenu Imenudecorator) {
        this.Imenudecorator=Imenudecorator;
    }

    public void createlayout(){
        Imenudecorator.createlayout();
    }

    @Override
    public BorderPane getLayout(){
        return Imenudecorator.getLayout();
    }
}