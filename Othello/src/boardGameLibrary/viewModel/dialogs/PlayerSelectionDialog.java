package boardGameLibrary.viewModel.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

/**
 * A simplified decorator of {@link Alert} for displaying messages related to a Player Selection scene.
 *
 * Does not decorate entire API of Alert and is not derived from its class.
 */
public class PlayerSelectionDialog {

    private Alert alert;

    public PlayerSelectionDialog(String text){

        this.alert = new Alert(Alert.AlertType.CONFIRMATION);

        this.alert.initStyle(StageStyle.UTILITY);
        this.alert.setHeaderText(null);
        this.alert.setTitle("Player selection");
        this.alert.setContentText(text);

    }

    /**
     * Creates an instance of PlayerSelectionDialog and displays it.
     * The window will close on confirmation.
     * @param text
     */
    public static void displayAndWait(String text){
        PlayerSelectionDialog dialog = new PlayerSelectionDialog(text);
        dialog.showAndWait();
    }

    /**
     * Displays dialog and waits for dialog button confirmation to close.
     */
    public void showAndWait(){
        this.alert.showAndWait()
                .filter(response -> response == ButtonType.CANCEL)
                .ifPresent(response -> this.alert.close());
    }
}
