package views.tournament.TournamentInformationView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import session.Session;
import tournament.ITournament;
import users.IPlayer;
import views.FXMLViewController;
import views.DimensionBinder;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The View Controller of the "TournamentInformation" view.
 *
 * Formats the tournament list and binds inner elements to surrounding container.
 */
public class TournamentInformationViewController extends FXMLViewController {

    List<ITournament> tournaments;
    IPlayer player;
    Session session = new Session();

    @FXML
    public BorderPane tournamentInformationContent;

    @FXML
    private VBox tournamentInfo;

    @FXML
    private Label activeTournamentsLabel;

    @FXML
    private VBox tournamentsList;

    /*@FXML
    public void handleMouseClick(MouseEvent arg0){
        System.out.println("clicked on: " + tournamentsList.getSelectionModel().getSelectedItem());
        new ApplyForTournamentDialog(session, tournamentsList.getSelectionModel().getSelectedItem());
    }*/

    public TournamentInformationViewController(){
        this.player = session.getPlayer();
        //tournaments = player.getAvailibleTournaments();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        viewTournamentList(player.getAvailibleTournaments());

        DimensionBinder.bindWidthToPercentageOfContainer(
                tournamentsList, 0.8, tournamentInfo
        );
    }

    public void viewTournamentList(List<ITournament> tournaments){
        activeTournamentsLabel.setText("Available Tournaments");

        TournamentApplyBox[] applyBoxes = tournaments.stream()
                .map(tournament -> new TournamentApplyBox(tournament, this.session.getAppliedTournaments()))
                .toArray(TournamentApplyBox[]::new);

        for (TournamentApplyBox applyBox : applyBoxes) {
            DimensionBinder.bindWidthToPercentageOfContainer(applyBox, 1, tournamentsList);
        }
        tournamentsList.getChildren().setAll(applyBoxes);

    }

    @Override
    public void closeView() {

    }

}
