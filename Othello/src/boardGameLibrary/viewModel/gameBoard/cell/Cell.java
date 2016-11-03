/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardGameLibrary.viewModel.gameBoard.cell;

import boardGameLibrary.viewModel.animation.MarkerAnimator;
import boardGameLibrary.viewModel.gameBoard.cellMarker.CellMarker;
import boardGameLibrary.viewModel.gameBoard.cellMarker.MarkerLayer;
import boardGameLibrary.viewModel.gameBoard.cellMarker.MarkerToParentTuple;
import javafx.scene.layout.StackPane;

/**
 * A Cell is a StackPane with two additional layered StackPanes.
 * The first and lowest layered is a layer for {@link CellMarker}s.
 * The second and above the mark layer, is a highlight layer.
 *
 * The highlight layer contain {@link CellMarker}s.
 */
public class Cell extends StackPane {
    // Indicate the row and column of this cell in the board
    private static final double CELL_MARK_PADDING_FACTOR = 0.85;
    private static final double CELL_HIGHLIGHT_PADDING_FACTOR = 0.75;

    private MarkerLayer markLayer;
    private MarkerLayer highlightLayer;

    /**
     * Creates a new Cell and adds to it an ID that can be
     * used for styling it with CSS.
     */
    public Cell() {
        this.setId("gameBoardCell");

        this.markLayer = new MarkerLayer();
        this.highlightLayer = new MarkerLayer();

        this.getChildren().add(this.markLayer);
        this.getChildren().add(this.highlightLayer);
    }

    /**
     * Marks the current cell with a {@link CellMarker}. It also removes
     * any preexisting marker in the mark layer.
     *
     * If there is already a mark in the mark layer, an animation will be activated.
     * Otherwise it will simple add the marker to the layer without animation.
     * @param marker
     */
    public void markCell(CellMarker marker) {
        if (this.markLayer.getChildren().size() > 0) {
            MarkerAnimator markerAnimator = new MarkerAnimator();
            markerAnimator.setMarkerLayer(this.markLayer);
            markerAnimator.setActors(this.markLayer.getMarkers().get(0), marker);
            markerAnimator.setAnimationTiming(180, 100);
            markerAnimator.playAnimation();
        }
        else {
            replaceMarker(marker);
        }
    }

    /**
     * Replaces all children of the highlight layer with a new highlight ({@link CellMarker}).
     * It also binds its width and height properties to fit the Cell.
     *
     * @param marker the CellMarker to be used as a highlighter.
     */
    public void highlightCell(CellMarker marker) {
        this.highlightLayer.setSingleMarker(marker);
        MarkerToParentTuple.create(marker, this.highlightLayer)
                .bindDimensions(CELL_HIGHLIGHT_PADDING_FACTOR);
    }

    /**
     * Removes the children of the highlight layer.
     */
    public void removeHighlight(){
        this.highlightLayer.getChildren().clear();
    }

    /**
     * Replaces all children of the Marker layer with a new {@link CellMarker}.
     * It also binds its width and height properties to fit the Cell.
     * @param marker
     */
    private void replaceMarker(CellMarker marker){
        this.markLayer.setSingleMarker(marker);
        MarkerToParentTuple.create(marker, this.markLayer)
                .bindDimensions(CELL_MARK_PADDING_FACTOR);
    }

}
