package advertisement;

import advertisement.AdvertSpot.AdvertHideButton;
import advertisement.AdvertSpot.AdvertSpot;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import views.ViewDimensionBinder;

/**
 * Created by August on 2016-11-03.
 */
public class Ad {

    private AdvertSpot advertSpot = new AdvertSpot();
    private ImageView advertImage = new ImageView();
    private Image advert = null;

    public Ad(String imageURL){
        this.advert = new Image(imageURL);
        this.setAdvertImage(this.advert);
        setAdvertHideButton();

    }

    private void setAdvertImage(Image image){
        this.advertImage.setImage(image);
        this.advertSpot.toFront();

        //this.advertSpot.getInnerPane().setBackground(new Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.advertSpot.getInnerPane().getChildren().add(this.advertImage);
        this.advertImage.preserveRatioProperty().set(true);

    }

    private void setAdvertHideButton(){
        AdvertHideButton hideButton = new AdvertHideButton();
        hideButton.stylize();
        hideButton.place(this.advertSpot);
        hideButton.setHideOnAction();
    }

    public void setAsTallAdvert(){
        //TODO method to bind ImageView dimensions as fitToWidth instead of fitToHeight
    }

    public void setAsWideAdvert(){
        this.advertImage.fitHeightProperty().bind(this.advertSpot.heightProperty());

        ViewDimensionBinder.bindOneToOneDimension(
                this.advertSpot.getInnerPane().minHeightProperty(),
                this.advertSpot.getInnerPane().maxHeightProperty(),
                this.advertSpot.heightProperty()
        );

        //Bind AdPane (the holder of the adverts) to the width of the image.
        ViewDimensionBinder.bindOneToOneDimension(
                this.advertSpot.getInnerPane().minWidthProperty(),
                this.advertSpot.getInnerPane().maxWidthProperty(),
                this.advert.widthProperty().multiply(this.advertSpot.heightProperty().divide(this.advert.heightProperty()))
        );
    }

    public AdvertSpot getAdvertSpot(){
        return this.advertSpot;
    }
}
