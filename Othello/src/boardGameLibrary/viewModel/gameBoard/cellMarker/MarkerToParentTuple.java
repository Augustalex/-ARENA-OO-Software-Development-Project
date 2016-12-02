package boardGameLibrary.viewModel.gameBoard.cellMarker;

import javafx.scene.layout.Pane;

/**
 * Created by August on 2016-10-22.
 */
public class MarkerToParentTuple {

    private CellMarker marker;
    private Pane parent;

    public MarkerToParentTuple(CellMarker marker, Pane parent){
        this.marker = marker;
        this.parent = parent;
    }

    public static MarkerToParentTuple create(CellMarker marker, Pane parent){
        return new MarkerToParentTuple(marker, parent);
    }

    public void bindDimensions(){
        this.marker.getWidthProperty().bind(this.parent.widthProperty());
        this.marker.getHeightProperty().bind(this.parent.heightProperty());
    }

    public void bindDimensions(double paddingFactor){
        this.marker.getWidthProperty().bind(this.parent.widthProperty().multiply(paddingFactor));
        this.marker.getHeightProperty().bind(this.parent.heightProperty().multiply(paddingFactor));
    }
}
