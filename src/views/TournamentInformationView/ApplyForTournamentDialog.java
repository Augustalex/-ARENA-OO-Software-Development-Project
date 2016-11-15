package views.TournamentInformationView;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import tournament.ITournament;
import tournament.Tournament;
import users.IPlayer;

import java.util.Optional;

/**
 * Created by Simon on 15/11/2016.
 */
public class ApplyForTournamentDialog {

    public ApplyForTournamentDialog(ITournament tournament, IPlayer player){

        showDialog(tournament, player);

    }

    private void showDialog(ITournament tournament, IPlayer player){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Tournament");
        alert.setHeaderText("Apply for " + tournament.getTournamentName());

        ButtonType applyBtn = new ButtonType("Apply");
        ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(applyBtn,cancelBtn);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == applyBtn){
            tournament.AddPlayer(player);
            player.addTournament(tournament);
        }
        else if(result.get() == cancelBtn){
            System.out.println("Did not apply for tournament");
        }

    }
}
