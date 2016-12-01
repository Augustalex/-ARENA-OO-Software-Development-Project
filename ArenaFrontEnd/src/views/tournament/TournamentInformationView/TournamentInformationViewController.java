package views.tournament.TournamentInformationView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import arena.session.AppliedTournaments;
import arena.session.Session;
import arena.tournament.ITournament;
import views.FXMLViewController;
import views.DimensionBinder;
import views.tournament.tournamentApplyBox.TournamentApplyBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The View Controller of the "TournamentInformation" view.
 *
 * Formats the arena.tournament list and binds inner elements to surrounding container.
 */
public class TournamentInformationViewController extends FXMLViewController {

    private Session session = Session.getSession();

    @FXML
    public BorderPane tournamentInformationContent;

    @FXML
    private VBox tournamentInfo;

    @FXML
    private Label activeTournamentsLabel;

    @FXML
    private VBox tournamentsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<ITournament> tournaments = this.session.getPlayer().getAvailibleTournaments();
        viewTournamentList(tournaments);

        DimensionBinder.bindWidthToPercentageOfContainer(
                tournamentsList, 0.8, tournamentInfo
        );
    }

    private void viewTournamentList(List<ITournament> tournaments){
        activeTournamentsLabel.setText("Available Tournaments");

        for(ITournament tournament : tournaments)
            addApplyBoxIntoList(tournament, this.session.getAppliedTournaments());
    }

    private void addApplyBoxIntoList(ITournament tournament, AppliedTournaments appliedTournaments){
        try {
            Region applyBoxPane = loadNewTournamentApplyBox(tournament, appliedTournaments);
            DimensionBinder.bindWidthToPercentageOfContainer(applyBoxPane, 0.95, tournamentsList);
            tournamentsList.getChildren().add(applyBoxPane);
            System.out.println("Added " + applyBoxPane + " to list.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Region loadNewTournamentApplyBox(ITournament tournament, AppliedTournaments appliedTournaments) throws IOException {
        return (Region) this.loadFXML(
                getClass().getResource(
                        "/views/tournament/tournamentApplyBox/TournamentApplyBox.fxml"),
                c -> new TournamentApplyBox(tournament, appliedTournaments)
        );
    }

    @Override
    public void closeView() {

    }

}
