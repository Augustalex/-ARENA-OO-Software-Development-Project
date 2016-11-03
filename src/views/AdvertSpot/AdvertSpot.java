package views.AdvertSpot;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import views.ViewDimensionBinder;

/**
 * An extension of stack pane that appends semantically
 * helpful methods for placing "adverts".
 */
public class AdvertSpot extends BorderPane{

    private StackPane adPane = new StackPane();

    public AdvertSpot(){
        this.setCenter(adPane);
    }

    /**
     * Fits the AdvertSpot to both dimensions of a Region.
     * @param container
     * @return
     */
    public AdvertSpot fitTo(Region container){
        this.minWidthProperty().bind(container.widthProperty());
        this.maxWidthProperty().bind(container.widthProperty());
        this.minHeightProperty().bind(container.heightProperty());
        this.maxHeightProperty().bind(container.heightProperty());

        return this;
    }

    /**
     * Contains a background inside the AdvertSpot. This background
     * is usually an BackgroundImage.
     * @param advert
     * @return
     */
    public AdvertSpot placeAd(Background advert){

        Image image = new Image("/coke.jpg");
        ImageView imageView = new ImageView(image);
        imageView.fitHeightProperty().bind(this.heightProperty());
        imageView.preserveRatioProperty().set(true);

        ViewDimensionBinder.bindOneToOneDimension(
                this.adPane.minHeightProperty(),
                this.adPane.maxHeightProperty(),
                this.heightProperty()
        );

        //Bind AdPane (the holder of the adverts) to the width of the image.
        ViewDimensionBinder.bindOneToOneDimension(
                this.adPane.minWidthProperty(),
                this.adPane.maxWidthProperty(),
                image.widthProperty().multiply(this.heightProperty().divide(image.heightProperty()))
        );

        this.adPane.setBackground(new Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));

        this.add(imageView);
        return this;
    }

    public void add(Node node){
        this.adPane.getChildren().add(node);
    }

    public void addAt(Node node, Pos position){

        this.adPane.getChildren().add(node);
    }

    public void setAll(Node node){
        this.adPane.getChildren().setAll(node);
    }

    public StackPane getInnerPane(){
        return this.adPane;
    }
}
