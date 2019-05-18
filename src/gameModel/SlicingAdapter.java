package gameModel;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SlicingAdapter {
    private MouseEvent event;
    private ThrowableObject object;

    public SlicingAdapter(MouseEvent event , ThrowableObject object){
        this.event = event;
        this.object = object;
    }

    public void sliceTheFruit(){
        System.out.println("event "+(event.getSceneX()-31));
        System.out.println("object " + object.getXLocation());

        if(event.getSceneX()-31 == object.getXLocation()){

            object.slice();
        }
    }

}
