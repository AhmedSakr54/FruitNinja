package gameModel;

public class NormalStrategy implements ILevelDifficutlyStrategy {

    @Override
    public int getBombIntensityCap() {
        return 20;
    }

    @Override
    public int getDifficutly() {
        return 200;
    }

    @Override
    public int getFruitSpeed() {
        return 9;
    }
}
