package boardGameLibrary.views.javaFxViews.settingsView.profileSettings;

import boardGameLibrary.playerProfileStore.PlayerProfile;
import boardGameLibrary.playerProfileStore.PlayerProfileStore;
import boardGameLibrary.views.javaFxViews.FXMLViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import utilities.RandomColor;
import utilities.router.Router;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-10-25.
 */
public abstract class ProfileSettingsViewController extends FXMLViewController {

    private static final String fxmlFileName = "ProfileSettingsView.fxml";

    @FXML
    protected TextField profileSettingsNameField;

    @FXML
    protected ColorPicker profileSettingsColorPicker;

    @FXML
    protected Button profileSettingsDone;

    public ProfileSettingsViewController(Pane container){
        super(container, ProfileSettingsViewController.fxmlFileName);
    }
}
