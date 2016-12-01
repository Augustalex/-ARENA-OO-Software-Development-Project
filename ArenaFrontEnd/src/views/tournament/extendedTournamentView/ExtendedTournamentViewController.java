package views.tournament.extendedTournamentView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import views.FXMLViewController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Simon on 01/12/2016.
 */
public class ExtendedTournamentViewController extends FXMLViewController {

    @FXML
    private Label TournamentName;

    @FXML
    private Label Matches;

    @FXML
    private VBox MatchList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void closeView() {
        System.out.println("LOL");
    }
}
