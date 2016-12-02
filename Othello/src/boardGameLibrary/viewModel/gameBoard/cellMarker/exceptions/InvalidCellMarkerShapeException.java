package boardGameLibrary.viewModel.gameBoard.cellMarker.exceptions;

/**
 * Created by August on 2016-10-12.
 */
public class InvalidCellMarkerShapeException extends RuntimeException {

    public InvalidCellMarkerShapeException(){
        super("Invalid shape for a Cell Marker.");
    }
}
