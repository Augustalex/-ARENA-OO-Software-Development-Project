package boardGameLibrary.views.javaFxViews.settingsView.profileSettings;

import boardGameLibrary.playerProfileStore.PlayerProfile;
import javafx.scene.layout.Pane;
import utilities.router.Router;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-10-25.
 */
public class EditPlayerViewController extends ProfileSettingsViewController{

    private PlayerProfile profile;

    public EditPlayerViewController(Pane container, PlayerProfile profile) {
        super(container);

        this.profile = profile;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        profileSettingsNameField.setText(this.profile.getName());
        profileSettingsColorPicker.setValue(this.profile.getColor());

        profileSettingsDone.setOnAction(e -> {
            this.profile.setName(profileSettingsNameField.getText().trim());
            this.profile.setColor(profileSettingsColorPicker.getValue());

            Router.getApplicationRouter().previous();
        });
    }
}
