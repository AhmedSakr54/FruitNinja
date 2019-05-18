package gameModel;

public class EasyStrategy implements ILevelDifficutlyStrategy {

    @Override
    public int getBombIntensityCap() {
        return 2;
    }

    @Override
    public int getDifficutly() {
        return 150;
    }

    @Override
    public int getFruitSpeed() {
        return 4;
    }
}
