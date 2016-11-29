package views.spectateMatch.watchMatch;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tournament.tournamentStyle.ITournamentStyle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * View controller for the Operators "Handle tournamentStyle" view. Sets up the TournamentStyle settings choices available, and sets
 * up routing for buttons to their corresponding "TournamentStyle" views.
 *
 * Extended ERROR handling for invalid input data needs to be fixed.
 */
public class HandleWatchMatchController implements Initializable{
    ITournamentStyle tournamentStyle;


    @FXML
    private Label test;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureView();
    }

    private void configureView(){
        test.setText("Hello world");
    }
}