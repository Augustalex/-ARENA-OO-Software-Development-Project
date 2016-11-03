package boardGameLibrary.viewModel.gameBoard.cellMarker.shapeMarkers;

import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Rectangle;

/**
 * Used to create a rectangular cell marker.
 */
public class RectangleMarker extends ShapeMarker {

    private Rectangle rectangle;

    /**
     * Requires a Rectangle to instantiate.
     * @param rectangle a new RectangleMarker (CellMarker)
     */
    public RectangleMarker(Rectangle rectangle){
        super(rectangle);

        this.rectangle = rectangle;
    }

    /**
     *
     * @return the width property of the rectangle.
     */
    public DoubleProperty getWidthProperty(){
        return this.rectangle.widthProperty();
    }

    /**
     *
     * @return the height property of the rectangle.
     */
    public DoubleProperty getHeightProperty(){
        return this.rectangle.heightProperty();
    }

}
