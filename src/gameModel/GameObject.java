package gameModel;

import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;

public interface GameObject {

    public ObjectType getObjectType();
    public int getXLocation();
    public int getYLocation();
    public int getMaxHeight();
    public int getInitialVelocity();
    public int getFallingVelocity();
    public boolean isSliced();
    public boolean hasMovedOffScreen();
    public void slice();
    public void move(double time);
    public ImageView[] getImageView();

}
