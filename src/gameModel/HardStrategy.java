package gameModel;

import java.util.Random;

public class HardStrategy implements  ILevelDifficutlyStrategy {
    private Random random = new Random();
    @Override
    public int getBombIntensityCap() {
        return random.nextInt(200) ;
    }

    @Override
    public int getDifficutly() {
        return random.nextInt(300);
    }

    @Override
    public int getFruitSpeed() {
        return 7;
    }
}
