package views.tournament.extendedTournamentView;

import arena.users.IPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Simon on 12/12/2016.
 */
public class LeaderBoard extends BorderPane implements Initializable {

    private IPlayer player;
    private Integer position;
    @FXML
    private Label playerPosition;

    @FXML
    private Label playerName;

    public LeaderBoard(IPlayer player, int position) {
        this.position = position;
        this.player = player;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerPosition.setText(position.toString());
        playerName.setText(player.getName());

    }
}
