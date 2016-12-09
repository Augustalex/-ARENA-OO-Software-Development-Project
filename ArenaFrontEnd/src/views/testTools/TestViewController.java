package views.testTools;

import arena.session.Session;
import arena.users.LeagueOwner;
import arena.users.Operator;
import arena.users.Player;
import arena.users.User;
import arena.users.advertiser.Advertiser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-12-09.
 */
public class TestViewController implements Initializable{

    @FXML
    private Button spectatorButton;

    @FXML
    private Button playerButton;

    @FXML
    private Button operatorButton;

    @FXML
    private Button leagueOwnerButton;

    @FXML
    private Button advertiserButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupQuickLoginButtons();

    }

    private void setupQuickLoginButtons(){

        spectatorButton.setOnMouseClicked(e -> {
            Session.getSession().setUser(User.createMockUser("Simon"));
        });

        operatorButton.setOnMouseClicked(e -> {
            Session.getSession().setUser(Operator.create("Johan"));
        });

        leagueOwnerButton.setOnMouseClicked(e -> {
            Session.getSession().setUser(LeagueOwner.create("Carlos"));
        });

        playerButton.setOnMouseClicked(e -> {
            Session.getSession().setUser(Player.create("Carlos"));
        });

        advertiserButton.setOnMouseClicked(e -> {
            Session.getSession().setUser(Advertiser.create("Patric"));
        });

    }
}
