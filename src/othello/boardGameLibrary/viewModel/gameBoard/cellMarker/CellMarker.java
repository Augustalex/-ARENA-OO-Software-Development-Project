package boardGameLibrary.viewModel.gameBoard.cellMarker;

import boardGameLibrary.boardGame.pawn.PawnDisplayModel;
import boardGameLibrary.viewModel.gameBoard.cellMarker.shapeMarkers.CircleMarker;
import boardGameLibrary.viewModel.gameBoard.cellMarker.shapeMarkers.RectangleMarker;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import boardGameLibrary.viewModel.gameBoard.cellMarker.exceptions.InvalidCellMarkerShapeException;

/**
 * Used to mark a cell in the {@link boardGameLibrary.views.javaFxViews.gameView.GameViewController}.
 *
 * There a different static creator functions within this class, one of
 * which is a special highlighting marker that comes styled (decorated).
 */
public interface CellMarker{

    /**
     * Given a Shape (from a set of legal shapes seen in the method)
     * it creates a suiting marker with the shape as its construction
     * argument.
     * @param shape The decorated Shape used to margin a Marker.
     * @return a new CellMarker.
     */
    static CellMarker create(Shape shape){
        if(shape instanceof Rectangle)
            return new RectangleMarker((Rectangle)shape);
        else if(shape instanceof Circle)
            return new CircleMarker((Circle)shape);
        else
            throw new InvalidCellMarkerShapeException();
    }

    /**
     * Given a displayModel it will with it margin a decorated shape
     * and then use that to margin a new CellMarker.
     * @param displayModel from a {@link boardGameLibrary.boardGame.pawn.Pawn}.
     * @return a new CellMarker.
     */
    static CellMarker create(PawnDisplayModel displayModel){
        return new PawnDisplayMarker(displayModel);
    }

    /**
     * Creates a new special decorated Highlight marker.
     * @param opacity can be an illustration of the importance of the new highlight.
     * @return a new CellMarker (which is distinct from other CellMarkers).
     */
    static CellMarker newHighlightMarker(double opacity){
        return new HighlightMarker(opacity);
    }

    /**
     * Returns a width property. In case of a Shape without a widthProperty
     * a substitute will be used to represent it (i.e. the radius of a circle).
     * @return a DoubleProperty to control width.
     */
    DoubleProperty getWidthProperty();

    /**
     * Returns a height property. In case of a Shape without a heightProperty
     * a substitute will be used to represent it (i.e. the radius of a circle).
     * @return a DoubleProperty to control height.
     */
    DoubleProperty getHeightProperty();

    /**
     * Returns the shape representing the marker.
     * @return a Shape.
     */
    Shape getShape();
}
