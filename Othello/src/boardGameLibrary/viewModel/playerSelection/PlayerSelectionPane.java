package boardGameLibrary.viewModel.playerSelection;

import boardGameLibrary.players.Player;
import boardGameLibrary.viewModel.dialogs.PlayerSelectionDialog;
import boardGameLibrary.viewModel.playerSelection.selectionConfirmation.SelectionConfirmationButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PlayerSelectionPane extends VBox {
//TODO separate to controller and view object
    private final PlayerSelectionBucket chosenPlayers;

    private ObservableList<Player> playerOptions;
    private ArrayList<SelectionConfirmationButton<Player>> confirmationButtons = new ArrayList<>();

    public PlayerSelectionPane(Player[] playerOptions, int numberOfPlayers){
        this.chosenPlayers = new PlayerSelectionBucket(numberOfPlayers);
        this.playerOptions = FXCollections.observableArrayList(playerOptions);

        for(int i = 0; i < numberOfPlayers; i++)
            addPlayerSelectionBox();

        listenToSelectionConfirmation();
    }

    public PlayerSelectionBucket chosenPlayersProperty(){
        return this.chosenPlayers;
    }

    private void listenToSelectionConfirmation(){
        for(SelectionConfirmationButton<Player> button : this.confirmationButtons){
            button.confirmedSelectionProperty().addListener((observable, oldValue, newValue) -> {


                if(button.isSelected()) {
                    if (this.chosenPlayers.contains(newValue)) {
                        PlayerSelectionDialog.displayAndWait("Player already selected.");
                        button.reset();
                    } else if (newValue != null)
                        this.chosenPlayers.add(newValue);
                }
                else
                    this.chosenPlayers.remove(oldValue);

            });
        }
    }

    private void addPlayerSelectionBox(){
        PlayerSelectionBox playerSelectionBox = new PlayerSelectionBox(this.playerOptions);
        SelectionConfirmationButton<Player> confirmationButton = new SelectionConfirmationButton<>(playerSelectionBox);
        PlayerSelectionRow selectionRow = new PlayerSelectionRow(playerSelectionBox, confirmationButton);
        this.getChildren().add(selectionRow);

        this.confirmationButtons.add(confirmationButton);
    }
    /*

    private Player getPlayerFromOptionName(String playerName){
        Player[] choice = this.playerOptions.parallelStream()
                .filter(player -> player.getName().equals(playerName))
                .toArray(Player[]::new);

        if(choice.length == 0)
            throw new NoSuchPlayerException();
        else if(choice.length > 1)
            throw new NonUniquePlayerNameException();
        else
            return choice[0];
    }
     */
}
