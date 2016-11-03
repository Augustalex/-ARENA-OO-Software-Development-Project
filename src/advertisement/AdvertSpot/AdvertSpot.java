package advertisement.AdvertSpot;

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

    public StackPane getInnerPane(){
        return this.adPane;
    }
}
