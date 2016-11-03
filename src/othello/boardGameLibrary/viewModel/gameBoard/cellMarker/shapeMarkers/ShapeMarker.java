package boardGameLibrary.viewModel.gameBoard.cellMarker.shapeMarkers;

import boardGameLibrary.viewModel.gameBoard.cellMarker.CellMarker;
import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Shape;

/**
 * Created by August on 2016-10-22.
 */
public abstract class ShapeMarker implements CellMarker {

    private Shape shape;

    public ShapeMarker(Shape shape){
        this.shape = shape;
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }
}
