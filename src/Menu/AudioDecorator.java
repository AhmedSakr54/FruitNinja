package Menu;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class AudioDecorator extends AddOnDecorator {

    public AudioDecorator(IMenu Imenudecorator) {
        super(Imenudecorator);
        createBackgroundMusic();
    }

    @Override
    public void createlayout() {
        super.createlayout();
    }

    public void createBackgroundMusic(){
        Media media=new Media(getClass().getResource("Background-Audio.mp3").toExternalForm());
        MediaPlayer mediaPlayer=new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
    }
}
