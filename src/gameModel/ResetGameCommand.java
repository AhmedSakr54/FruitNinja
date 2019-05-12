package gameModel;

import Controllers.FirstController;
import View.GameView;

public class ResetGameCommand implements ICommand{

    private FirstController controller;

    public ResetGameCommand(FirstController controller){
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.resetGame();
    }
}
