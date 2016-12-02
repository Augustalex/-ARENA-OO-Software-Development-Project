package advertisement.adDisplay;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import views.DimensionBinder;

/**
 * A stylized button that will appear on the top right corner of an advert.
 * On action it will hide the advert.
 */
public class AdvertHideButton extends StackPane {

    private final Button hideButton = new Button();
    private FxAdDisplay advertSpot = null;

    public AdvertHideButton(){
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().add(hideButton);
    }

    public void place(FxAdDisplay advertSpot){

        DimensionBinder.bindTwoToOneDimensions(this, advertSpot.widthProperty().multiply(0.1));
        this.toFront();

        System.out.println("Placed the button " + this);
        advertSpot.getInnerPane().getChildren().add(this);
        DimensionBinder.fixedBindTo(this, advertSpot.getInnerPane());

        this.hideButton.setId("hideAdButton");
        this.setPadding(new Insets(0, 10, 10, 10));
        this.advertSpot = advertSpot;
    }

    public void stylize(){
        this.hideButton.setBackground(new Background(new BackgroundImage(
                new Image("/xButtonWhite.png"),
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
