package boardGameLibrary.views.javaFxViews.settingsView;

import boardGameLibrary.playerProfileStore.PlayerProfile;
import boardGameLibrary.playerProfileStore.PlayerProfileStore;
import boardGameLibrary.views.javaFxViews.FXMLViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import utilities.router.Router;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-10-25.
 */
public class SettingsViewController extends FXMLViewController{

    private static final String fxmlFileName = "SettingsView.fxml";

    private PlayerProfileStore store;

    @FXML
    private ListView<PlayerProfile> profilesList;

    @FXML
    private Button addProfileButton;

    @FXML
    private Button removeProfileButton;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button back;

    public SettingsViewController(Pane container, PlayerProfileStore store) {
        super(container, SettingsViewController.fxmlFileName);

        this.store = store;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(PlayerProfile profile : this.store.getStoreCopy())
            profilesList.getItems().add(profile);

        addProfileButton.setOnAction(e -> routeToAddPlayerProfileView());

        editProfileButton.setOnAction(e -> routeToEditPlayerProfileView());

        removeProfileButton.setOnAction(e -> removeSelectedPlayer());

        back.setOnAction(e -> {
            Map<String, Object> state = new HashMap<>();
            state.put("PlayerProfileStore", this.store);
            Router.getApplicationRouter().route("MainView", state);
        });
    }

    private void routeToAddPlayerProfileView(){
        Map<String, Object> dependencies = new HashMap<>();
        dependencies.put("PlayerProfileStore", this.store);
        Router.getApplicationRouter().route("ProfileSettingsView", dependencies);
    }

    private void routeToEditPlayerProfileView(){
        Map<String, Object> dependencies = new HashMap<>();
        dependencies.put("PlayerProfile", this.profilesList.getSelectionModel().getSelectedItem());
        Router.getApplicationRouter().route("ProfileSettingsView", dependencies);
    }

    private void removeSelectedPlayer(){
        PlayerProfile selected = profilesList.getSelectionModel().getSelectedItem();
        this.store.remove(selected);
        Router.getApplicationRouter().reload();
    }
}
