package Menu;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LogoDecorator extends AddOnDecorator {

    ImageView ninjaImage;
    Text logoText;

    public LogoDecorator(IMenu Imenudecorator) {
        super(Imenudecorator);
    }

    @Override
    public void createlayout() {
        super.createlayout();
        createLogo();
    }

    public void createLogo(){
        createImageLogo();
        createTextLogo();
        addLogo();
        addImageLogoEffects();
        addTextLogoEffects();
    }

    public void createImageLogo(){
        ninjaImage=new ImageView("Menu/Resources/Ninja.png");
        ninjaImage.setFitHeight(250);
        ninjaImage.setFitWidth(250);
    }

    public void createTextLogo(){
        logoText=new Text("\uD835\uDD71\uD835\uDD97\uD835\uDD9A\uD835\uDD8E\uD835\uDD99 \uD835\uDD79\uD835\uDD8E\uD835\uDD93\uD835\uDD8F\uD835\uDD86");
        logoText.setFont(new Font(50));
        logoText.setFill(Color.YELLOW);
    }

    public void addLogo(){
        VBox logo=new VBox();
        logo.setAlignment(Pos.CENTER);
        logo.setSpacing(5);
        logo.getChildren().addAll(ninjaImage,logoText);
        this.Imenudecorator.getLayout().setTop(logo);
    }

    public void addImageLogoEffects(){
        ninjaImage.setOnMouseEntered(e->{
            ninjaImage.setEffect(new DropShadow());
        });

        ninjaImage.setOnMouseExited(e->{
            ninjaImage.setEffect(null);
        });
    }

    public void addTextLogoEffects(){
        logoText.setOnMouseEntered(e->{
            logoText.setEffect(new DropShadow());
        });

        logoText.setOnMouseExited(e->{
            logoText.setEffect(null);
        });
    }
}
