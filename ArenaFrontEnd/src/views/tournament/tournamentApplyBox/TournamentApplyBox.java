package views.tournament.tournamentApplyBox;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import session.*;
import tournament.ITournament;
import views.DimensionBinder;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public TournamentApplyBox(ITournament tournament, AppliedTournaments appliedTournaments){
        this.tournament = tournament;
        this.appliedTournaments = appliedTournaments;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupApplyLabel(tournamentLabel);
        setupApplyButton(applyButton);

       // bindDimensionsOfLeftAndRightPaneSides(leftSide, rightSide);
    }

    private void setupApplyLabel(Label label){
        label.setText(tournament.getTournamentMetaInformation().getName());
    }

    /**
     * Button action wont work at a certain area because of the overlapping ad that, even if removed,
     * will not allow things below it to be clicked.
     * @Button button
     */
    private void setupApplyButton(Button button){
        System.out.println(this.widthProperty().doubleValue());
        //Set action event handler
        button.setOnAction(e -> {
            System.out.println("Applied for tournament!");
            appliedTournaments.applyToTournament(tournament);
            tournament.applyPlayer(Session.getSession().getPlayer());
        });
    }
}
