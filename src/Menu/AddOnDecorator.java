package Menu;


public abstract class AddOnDecorator implements IMenu {
    protected IMenu Imenudecorator;

    public AddOnDecorator(IMenu Imenudecorator) {
        this.Imenudecorator=Imenudecorator;
    }

    public void createlayout(){
        Imenudecorator.createlayout();
    }
}
