package boardGameLibrary.viewModel.playerSelection;

import boardGameLibrary.viewModel.playerSelection.selectionConfirmation.SelectionConfirmationButton;
import javafx.scene.layout.HBox;

/**
 * Created by August on 2016-10-23.
 */
public class PlayerSelectionRow extends HBox {

    public PlayerSelectionRow(PlayerSelectionBox selectionBox, SelectionConfirmationButton confirmationButton){
        this.getChildren().addAll(selectionBox, confirmationButton);
    }
}
