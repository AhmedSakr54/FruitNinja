package gameModel;

import java.util.concurrent.ThreadPoolExecutor;

public class MoveCommand implements ICommand {

    private ThrowableObject fruit;

    public MoveCommand(ThrowableObject fruit){
        this.fruit = fruit;
    }
    @Override
    public void execute() {
        this.fruit.move(2);
    }
}
