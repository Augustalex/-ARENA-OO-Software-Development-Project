package views.handleAdvertisement;

import arena.advertisement.ad.Ad;
import arena.advertisement.ad.ImageAd;
import arena.advertisement.ad.PreferredAdFactory;
import arena.advertisement.adPreference.AdPreferenceFactory;
import arena.advertisement.adRepository.AdRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import arena.metaInformation.advertisementMetaInformation.AdvertisementMetaInformation;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Johan on 2016-11-29.
 */
public class handleAdvertisementController implements Initializable {
    private String[] preferences;
    private CheckBox[] checkboxes;

    @FXML
    private BorderPane handleAdvertisementWindow;

    @FXML
    private VBox inputVBox;

    @FXML
    private TextField sourceField;

    @FXML
    private TextField amountField;

    @FXML
    private VBox preferenceList;

    @FXML
    private Button submitAd;

    @FXML
    private TextField adName;

    @FXML
    private TextArea adDescription;

    @FXML
    private Text confirmationText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputVBox.setPadding(new Insets(25,0,25,0));
        preferenceList.setPadding(new Insets(0,0,25,0));
        setPreferenceList();
        submitAd.setOnAction(e->submitHandler());
    }

    private void setPreferenceList(){
        preferences = new String[2];
        preferences[0] = "Play View";
        preferences[1] = "Main View";
        Label[] preferenceLabels = new Label[2];
        checkboxes = new CheckBox[2];

        for(int i = 0; i < 2; i++){
            checkboxes[i] = new CheckBox();
            preferenceLabels[i] = new Label(preferences[i], checkboxes[i]);
            preferenceLabels[i].setContentDisplay(ContentDisplay.LEFT);
            preferenceList.getChildren().add(preferenceLabels[i]);
            preferenceLabels[i].setStyle("-fx-text-fill: white");
        }
    }

    private void submitHandler(){
        String source = sourceField.getText();
        String amount = amountField.getText();
        String[] selectedPreferences = new String[2];
        String name = adName.getText();
        String description = adDescription.getText();

        for(int i = 0; i < 2; i++){
            if(checkboxes[i].isSelected())
                selectedPreferences[i] = preferences[i];
        }
        AdvertisementMetaInformation adMetaInformation = new AdvertisementMetaInformation(name, description);
        AdRepository.get().addPreferredAd(PreferredAdFactory.newPreferredAd(source,
                AdPreferenceFactory.newPlayViewPreference(), adMetaInformation));
        confirmationText.setText("Advertisement added and will be shown in prefered adspot");
    }
}
