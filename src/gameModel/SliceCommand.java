package gameModel;

public class SliceCommand implements ICommand {

    private ThrowableObject fruit;

    public SliceCommand(ThrowableObject fruit){
        this.fruit = fruit;
    }

    @Override
    public void execute() {
        fruit.slice();
    }
}
