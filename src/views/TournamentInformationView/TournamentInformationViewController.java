package views.TournamentInformationView;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import views.FXMLViewController;
import views.DimensionBinder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The View Controller of the "TournamentInformation" view.
 *
 * Formats the tournament list and binds inner elements to surrounding container.
 */
public class TournamentInformationViewController extends FXMLViewController {

    @FXML
    public BorderPane tournamentInformationContent;

    @FXML
    private VBox tournamentInfo;

    @FXML
    private Label activeGamesLabel;

    @FXML
    private ListView tournamentsList;

    public TournamentInformationViewController(){
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        viewTournamentList();

        DimensionBinder.bindWidthToPercentageOfContainer(
                tournamentsList, 0.8, tournamentInfo
        );
    }

    public void viewTournamentList(){ //skickar med ArrayList tournaments
        activeGamesLabel.setText("Active Games");

        ObservableList<String> tournaments = tournamentsList.getItems();

        //kan göras om till en loop som fyller på alla tournaments
        //från en array.
        tournaments.add("Coke Tournament");
        tournaments.add("Championship 2016");
        tournaments.add("'Unleash the August");

        tournamentsList.setItems(tournaments);
        tournamentsList.setPrefWidth(10);
    }

    @Override
    public void closeView() {

    }

}
