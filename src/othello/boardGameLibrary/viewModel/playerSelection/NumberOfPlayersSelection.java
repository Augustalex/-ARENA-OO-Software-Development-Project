package boardGameLibrary.viewModel.playerSelection;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

/**
 *  An HBox containing a selection box. This box contains a series of integers
 *  from 2 to a max number of players argument.
 *
 */
public class NumberOfPlayersSelection extends HBox {

    private ComboBox<Integer> selectionBox = new ComboBox<>();

    public NumberOfPlayersSelection(int maxNumberOfPlayers){
        for(int i = 1; i < maxNumberOfPlayers; i++)
            selectionBox.getItems().add(i + 1);

        this.getChildren().add(this.selectionBox);

        this.setId("numberOfPlayersSelection");

        this.selectionBox.setId("numberOfPlayersSelectionBox");
    }

    /**
     * The selection property of the ComboBox.
     * @return Selection property.
     */
    public ObjectProperty<Integer> selectedValue(){
        return this.selectionBox.valueProperty();
    }
}

/*
class ActiveUser{

    String playerName;
    String ip;

}

interface DatabaseConnection{

    ActiveUser[] getActiveUsers();

    void addActiveUser(ActiveUser activeUser);

    void removeActiveUser(ActiveUser activeUser);

}*/