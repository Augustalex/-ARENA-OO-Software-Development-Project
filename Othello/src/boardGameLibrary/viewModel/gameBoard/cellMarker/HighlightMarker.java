package boardGameLibrary.viewModel.gameBoard.cellMarker;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * A decorated {@link CellMarker}. Receives a opacity as a double, which can be used to
 * denote importance (i.e. score/value).
 *
 */
public class HighlightMarker implements CellMarker {

    private CellMarker cellMarker;

    public HighlightMarker(double opacity){
        Circle highlight = new Circle();

        highlight.setFill(Color.TRANSPARENT);
        highlight.setStroke(Color.YELLOWGREEN);
        highlight.setOpacity(opacity);
        highlight.setStrokeWidth(3);

        this.cellMarker = CellMarker.create(highlight);
    }

    @Override
    public DoubleProperty getWidthProperty() {
        return this.cellMarker.getWidthProperty();
    }

    @Override
    public DoubleProperty getHeightProperty() {
        return this.cellMarker.getHeightProperty();
    }

    @Override
    public Shape getShape() {
        return this.cellMarker.getShape();
    }
}
