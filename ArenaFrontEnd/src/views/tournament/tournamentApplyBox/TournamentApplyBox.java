package views.tournament.tournamentApplyBox;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import arena.session.*;
import arena.tournament.ITournament;
import views.tournament.extendedTournamentView.TournamentLobbyInitiator;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * A horizontally aligned box containing a label with the tournament name,
 * as well as a button that when clicked will apply the Session active player
 * to that tournament.
 *
 * The Session object that this class depends on is accessed statically and
 * is from this class perspective treated as a singleton. If the reality is different
 * this class may not function as expected.
 */
public class TournamentApplyBox extends BorderPane implements Initializable{

    private ITournament tournament;
    private AppliedTournaments appliedTournaments;

    @FXML
    private Label tournamentLabel;

    @FXML
    private Button applyButton;

    @FXML
    private Button tourLobbyButton;

    public TournamentApplyBox(ITournament tournament, AppliedTournaments appliedTournaments){
        this.tournament = tournament;
        this.appliedTournaments = appliedTournaments;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buttonHandler();

    }

    private void setupApplyLabel(Label label){
        label.setText(tournament.getTournamentMetaInformation().getName());
    }



    private void buttonHandler(){

        setupApplyLabel(tournamentLabel);

        tourLobbyButton.setDisable(true);
        tourLobbyButton.setStyle("-fx-base: #766F70");

        applyButton.setOnAction(e -> {
            applyButton.setText("Applied");
            appliedTournaments.applyToTournament(tournament);
            tournament.applyPlayer(Session.getSession().getPlayer());
            applyButton.setDisable(true);
            applyButton.setStyle("-fx-base: #766F70");
            tourLobbyButton.setDisable(false);
            tourLobbyButton.getStyleClass().add("applyButtons");
        });
        tourLobbyButton.setOnAction(e -> {
            System.out.println("New window opened!");
            new TournamentLobbyInitiator().newLobbyView(tournament);
        });

    }
}
