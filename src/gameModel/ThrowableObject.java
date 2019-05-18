package gameModel;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import Controllers.*;

import java.util.Random;

public class ThrowableObject implements GameObject {

    private Random random = new Random();

    private ObjectType type;
    private ImageView[] images = new ImageView[3];
    private int xLocation = random.nextInt(850) ;
    private int yLocation;
    private int intialVelocity;
    private int fallingVelocity;
    private boolean sliced = false ;
    private boolean movedOffScreen = false;
    private int maxHeight = 90;
    private boolean reachedMaxHeight = false;

    public boolean isReachedMaxHeight() {
        return reachedMaxHeight;
    }

    public void setReachedMaxHeight(boolean reachedMaxHeight) {
        this.reachedMaxHeight = reachedMaxHeight;
    }

    public void setType(ObjectType type) {
        this.type = type;
        images[0] = new ImageView(type.getFirstURL());
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(images[0]);
        rotate.setFromAngle(0);
        rotate.setToAngle(360);
        rotate.setDuration(Duration.seconds(5));
        rotate.setCycleCount(rotate.INDEFINITE);
        rotate.play();
        try {
            images[1] = new ImageView(type.getSecondURL());
            images[2] = new ImageView(type.getThridURL());
        }catch (NullPointerException e){
            images[1] = null;
            images[2] = null;
        }
    }

    public void setxLocation(int xLocation) {

        this.xLocation = xLocation;
    }

    public void setyLocation(int yLocation) {

        this.yLocation = yLocation;
    }

    public void setIntialVelocity(int intialVelocity)
    {
        this.intialVelocity = intialVelocity;
    }

    public void setFallingVelocity(int fallingVelocity) {

        this.fallingVelocity = fallingVelocity;
    }

    public void setSliced(boolean sliced)
    {
        this.sliced = sliced;
    }

    public void setMovedOffScreen(boolean movedOffScreen) {
        this.movedOffScreen = movedOffScreen;
    }

    @Override
    public ObjectType getObjectType() {
        return type;
    }

    @Override
    public int getXLocation() {
        return  xLocation;
    }

    @Override
    public int getYLocation() {
        return yLocation;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public int getInitialVelocity() {
        return intialVelocity;
    }

    @Override
    public int getFallingVelocity() {
        return fallingVelocity;
    }

    @Override
    public boolean isSliced() {
        return sliced;
    }

    @Override
    public boolean hasMovedOffScreen() {
        return movedOffScreen;
    }

    @Override
    public void slice() {
        images[0].setOnMouseExited(e->{
            sliced = true;
        });

    }

    @Override
    public void move(double time) {
        if(this.type != ObjectType.FATALBOMB && this.type !=ObjectType.DAMAGEBOMB) {
            this.setyLocation(this.getYLocation() + 50);
            double x = (double) this.getYLocation();
            this.getImageView()[1].setLayoutY(x);
            this.getImageView()[2].setLayoutY(x);

            TranslateTransition transition1 = new TranslateTransition();
            TranslateTransition transition2 = new TranslateTransition();
            RotateTransition rotate1 = new RotateTransition(Duration.seconds(time));
            RotateTransition rotate2 = new RotateTransition(Duration.seconds(time));
            rotate1.setNode(images[1]);
            rotate2.setNode(images[2]);
            rotate1.setFromAngle(0);
            rotate2.setFromAngle(0);
            rotate1.setToAngle(-150);
            rotate2.setToAngle(150);
            transition1.setNode(this.getImageView()[1]);
            transition2.setNode(this.getImageView()[2]);
            transition1.setToY(1000);
            transition1.setToX(-200);
            transition2.setToY(1000);
            transition2.setToX(200);
            transition1.setDuration(Duration.seconds(time));
            transition2.setDuration(Duration.seconds(time));
            transition1.play();
            transition2.play();
            rotate1.play();
            rotate2.play();
        }
    }

    @Override
    public ImageView[] getImageView() {
        return this.images;
    }

    public void throwObject(){
    }


}
