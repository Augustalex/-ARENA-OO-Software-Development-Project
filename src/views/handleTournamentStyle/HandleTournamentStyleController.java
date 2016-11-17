package views.handleTournamentStyle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import tournament.ITournament;
import tournament.tournamentStyle.ITournamentStyle;
import tournament.tournamentStyle.TournamentStyle;
import tournament.tournamentStyle.TournamentStyleFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Johan on 2016-11-15.
 */
public class HandleTournamentStyleController implements Initializable{
    ITournamentStyle tournamentStyle;
    @FXML
    private GridPane TournamentStyleWindow;

    @FXML
    private TextField name;

    @FXML
    private TextField size;

    @FXML
    private CheckBox groupsChoice;

    @FXML
    private TextField noOfGroups;

    @FXML
    private TextField rounds;

    @FXML
    private TextField groupWinners;

    @FXML
    private CheckBox eliminationChoice;

    @FXML
    private TextField bestOfGames;

    @FXML
    private TextArea description;

    @FXML
    private Button submit;

    @FXML
    private Text confirmationText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit.setOnAction(e->constructTournamentStyle());

    }

    public ITournamentStyle getNewTournamentStyle(){
        return tournamentStyle;
    }

    private void constructTournamentStyle(){
        tournamentStyle = TournamentStyleFactory.newTournamentStyle();
        tournamentStyle.setTournamentStyleName(name.getText());
        tournamentStyle.setTournamentStyleDescription(description.getText());
        tournamentStyle.setTournamentSize(Integer.parseInt(size.getText()));
        if(eliminationChoice.isSelected()) {
            tournamentStyle.setEliminationSettings();
            tournamentStyle.getEliminationSettings().setBestOf(Integer.parseInt(bestOfGames.getText()));
        }
        if(groupsChoice.isSelected()) {
            tournamentStyle.setGroupSettings();
            tournamentStyle.getGroupSettings().setGroupAmount(Integer.parseInt(noOfGroups.getText()));
            tournamentStyle.getGroupSettings().setMaxWinners(Integer.parseInt(groupWinners.getText()));
            tournamentStyle.getGroupSettings().setRounds(Integer.parseInt(rounds.getText()));
        }
        System.out.print(tournamentStyle.toString());
        if(!testTournamentStyle(tournamentStyle)){
            showWarningDialog();
            tournamentStyle = null;
        }
        if(tournamentStyle != null) {
            confirmationText.setText("TournamentStyle is created");
            TournamentStyleFactory.setTournamentStyle(tournamentStyle);
        }
    }

    private boolean testTournamentStyle(ITournamentStyle tournamentStyle) {
        if(tournamentStyle.getGroupSettings() != null){
            if(tournamentStyle.getTournamentSize()%tournamentStyle.getGroupSettings().getGroupAmount() != 0)
                return false;
        }
        if(tournamentStyle.getEliminationSettings() != null) {
            if (tournamentStyle.getEliminationSettings().getBestOf() % 2 == 0)
                return false;
        }
        return true;
    }

    private void showWarningDialog(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error!");
        alert.setHeaderText("New tournamentstyle contains errors.");
        alert.setContentText("Your tournament style is not createt\n" +
                "Please check your input and re-submit to create\n" +
                "the tournament style");
        alert.showAndWait();
    }

}
