package Menu;


import javafx.scene.layout.AnchorPane;

public abstract class AddOnDecorator implements IMenu {
    protected IMenu Imenudecorator;

    public AddOnDecorator(IMenu Imenudecorator) {
        this.Imenudecorator=Imenudecorator;
    }

    public void createlayout(){
        Imenudecorator.createlayout();
    }

    @Override
    public AnchorPane getLayout(){
        return null;
    }
}
