package boardGameLibrary.views.javaFxViews.settingsView.profileSettings;

import boardGameLibrary.playerProfileStore.PlayerProfile;
import boardGameLibrary.playerProfileStore.PlayerProfileStore;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import utilities.RandomColor;
import utilities.router.Router;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-10-25.
 */
public class AddProfileViewController extends ProfileSettingsViewController {

    protected PlayerProfileStore store;

    public AddProfileViewController(Pane container, PlayerProfileStore store) {
        super(container);

        this.store = store;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        profileSettingsColorPicker.setValue(RandomColor.nextColor());

        profileSettingsDone.setOnAction(e -> {
            String name = profileSettingsNameField.getText().trim();
            Color color = profileSettingsColorPicker.getValue();

            store.store(new PlayerProfile(name, color));
            Router.getApplicationRouter().previous();
        });


    }
}
