package views.AdvertSpot;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import views.ViewDimensionBinder;

/**
 * A stylized button that will appear on the top right corner of an advert.
 * On action it will hide the advert.
 */
public class AdvertHideButton extends StackPane {

    private Button hideButton = new Button();
    private AdvertSpot advertSpot = null;

    public AdvertHideButton(){
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().add(hideButton);
    }

    public void place(AdvertSpot advertSpot){

        ViewDimensionBinder.bindTwoToOneDimensions(this, advertSpot.widthProperty().multiply(0.1));
        this.toFront();

        System.out.println("Placed the button " + this);
        advertSpot.add(this);
        ViewDimensionBinder.fixedBindTo(this, advertSpot.getInnerPane());

        this.advertSpot = advertSpot;
    }

    public void stylize(){
        this.hideButton.setBackground(new Background(new BackgroundImage(
                new Image("/xButton.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        )));
    }

    public void setHideOnAction(){
        if(this.advertSpot != null)
            this.hideButton.setOnAction(e -> this.advertSpot.setVisible(false));
    }
}
