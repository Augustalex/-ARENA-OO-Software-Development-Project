package boardGameLibrary.viewModel.playerSelection.selectionConfirmation;

import javafx.beans.property.ObjectProperty;

/**
 * For input of selection that requires a confirming action.
 * @param <T> Type of option in source of selection (input).
 */
public interface SelectionConfirmation <T>{

    ObjectProperty<T> confirmedSelectionProperty();

    void reset();
}
