package views.tournament.extendedTournamentView;

import arena.tournament.ITournament;
import arena.tournament.match.IMatch;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import spectate.SpectateTable;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Simon on 02/12/2016.
 */

public class MatchList extends BorderPane implements Initializable{

    private IMatch match;
    @FXML
    private Label matchLabel;

    public MatchList(IMatch match){
        this.match = match;
    }

   // public IMatch getMatch(){
     //   return SpectateTable.get().getAvailableMatches(tournament);
    //}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        matchLabel.setText(match.getPlayersInMatch().get(0).getName() + " vs " + match.getPlayersInMatch().get(1).getName());
        matchLabel.setStyle("-fx-prompt-text: white");
    }
}
