package boardGameLibrary.viewModel.playerSelection.selectionConfirmation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

/**
 * Extends CheckBox and confirms a selection among options. These options are
 * of type T.
 * @param <T> Type of options.
 */
public class SelectionConfirmationButton<T> extends CheckBox implements SelectionConfirmation<T>{

    private ObjectProperty<T> confirmedSelection = new SimpleObjectProperty<>();

    public SelectionConfirmationButton(ComboBox<T> selectionBox){
        this.selectedProperty().addListener(e -> {
            if(this.selectedProperty().get())
                this.confirmedSelection.set(selectionBox.getValue());
        });
    }

    @Override
    public ObjectProperty<T> confirmedSelectionProperty() {
        return this.confirmedSelection;
    }

    @Override
    public void reset() {
        this.selectedProperty().set(false);
        this.confirmedSelectionProperty().set(null);
    }
}
