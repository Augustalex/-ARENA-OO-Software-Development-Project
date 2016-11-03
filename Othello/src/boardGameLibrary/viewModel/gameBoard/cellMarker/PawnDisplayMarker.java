package boardGameLibrary.viewModel.gameBoard.cellMarker;

import boardGameLibrary.boardGame.pawn.PawnDisplayModel;
import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.Shape;

/**
 * Decorator class for a {@link CellMarker} created from a {@link PawnDisplayModel}.
 */
public class PawnDisplayMarker implements CellMarker {

    private CellMarker cellMarker;

    public PawnDisplayMarker(PawnDisplayModel displayModel){
        Shape shape = displayModel.getShape();
        shape.setFill(displayModel.getPaint());

        this.cellMarker = CellMarker.create(shape);
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
