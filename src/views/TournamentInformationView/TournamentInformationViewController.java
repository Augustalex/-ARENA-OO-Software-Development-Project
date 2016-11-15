package views.TournamentInformationView;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import tournament.ITournament;
import tournament.Tournament;
import users.IPlayer;
import views.FXMLViewController;
import views.DimensionBinder;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The View Controller of the "TournamentInformation" view.
 *
 * Formats the tournament list and binds inner elements to surrounding container.
 */
public class TournamentInformationViewController extends FXMLViewController {

    List<ITournament> tournaments;

    @FXML
    public BorderPane tournamentInformationContent;

    @FXML
    private VBox tournamentInfo;

    @FXML
    private Label activeTournamentsLabel;

    @FXML
    private ListView tournamentsList;

    @FXML
    public void handleMouseClick(MouseEvent arg0){
        System.out.println("clicked on: " + tournamentsList.getSelectionModel().getSelectedItem());
        new ApplyForTournamentDialog(tournaments.);
    }

    public TournamentInformationViewController(IPlayer player){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeTourList(player);
        viewTournamentList();

        DimensionBinder.bindWidthToPercentageOfContainer(
                tournamentsList, 0.8, tournamentInfo
        );
    }

    private List<ITournament> initializeTourList(IPlayer player){
         tournaments = player.getAvailibleTournaments(player.getLeagues());
        return tournaments;
    }

    public void viewTournamentList(){ //skickar med ArrayList tournaments
        activeTournamentsLabel.setText("Active Tournaments");

        tournaments = tournamentsList.getItems();

        //kan göras om till en loop som fyller på alla tournaments
        //från en array.
//        tournaments.add("Coke Tournament");
//        tournaments.add("Championship 2016");


        tournamentsList.setItems(tournaments);
        tournamentsList.setPrefWidth(10);
    }

    @Override
    public void closeView() {

    }

}
