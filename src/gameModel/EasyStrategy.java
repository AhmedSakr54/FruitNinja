package gameModel;

public class EasyStrategy implements ILevelDifficutlyStrategy {

    @Override
    public int getBombIntensityCap() {
        return 5;
    }

    @Override
    public int getDifficutly() {
        return 500;
    }

    @Override
    public int getFruitSpeed() {
        return 4;
    }
}
