package views.handleAdvertisement.handleNewAdvertisement;

import arena.advertisement.ad.IPreferredAd;
import arena.advertisement.ad.PreferredAdFactory;
import arena.advertisement.adPreference.AdPreferenceFactory;
import arena.advertisement.adRepository.AdRepository;
import arena.metaInformation.AdSchemeMetaInformation.AdSchemeMetaInformation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import arena.metaInformation.advertisementMetaInformation.AdvertisementMetaInformation;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Johan on 2016-11-29.
 */
public class HandleAdvertisementController implements Initializable {
    private String[] preferences;
    private CheckBox[] checkboxes;

    private ObjectProperty<IPreferredAd> adProperty = new SimpleObjectProperty<>();

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
        handleAdvertisementWindow.setPadding(new Insets(0, 0, 0, 30));
        inputVBox.setPadding(new Insets(25, 0, 25, 0));
        preferenceList.setPadding(new Insets(0, 0, 25, 0));
        setPreferenceList();
        submitAd.setOnAction(e->{submitHandler();});
    }

    private void setPreferenceList() {
        preferences = new String[2];
        preferences[0] = "Play View";
        preferences[1] = "Main View";
        Label[] preferenceLabels = new Label[2];
        checkboxes = new CheckBox[2];
        AdSchemeMetaInformation[] helpInformation = new AdSchemeMetaInformation[2];
        helpInformation[0] = new AdSchemeMetaInformation("Play View Scheme",
                "Your adProperty will be shown in the Play view in the Arena.\n It will be shown for "
                        + "max 5 seconds and for\n every display your account will be debited $0.25"
                        + "\nAd will be closeable.");
        helpInformation[1] = new AdSchemeMetaInformation("Main View Scheme",
                "Your adProperty will be shown in the Main view in the Arena.\n It will be shown for "
                        + "max 10 seconds and for\n every display your account will be debited $0.5"
                        + "\nAd will be closeable.");


        for (int i = 0; i < 2; i++) {
            HBox line = new HBox(20);
            checkboxes[i] = new CheckBox();
            preferenceLabels[i] = new Label(preferences[i], checkboxes[i]);
            preferenceLabels[i].setContentDisplay(ContentDisplay.LEFT);
            Text help = new Text("?");
            help.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            help.setFill(Color.WHITE);
            //help.setOnMouseEntered(e->showHelp(helpInformation[0]));

            addTooltip(help, helpInformation[0]);

            line.getChildren().addAll(preferenceLabels[i], help);

            preferenceList.getChildren().add(line);
            preferenceLabels[i].setStyle("-fx-text-fill: white");
        }
    }

    private void addTooltip(Text help, AdSchemeMetaInformation adSchemeMetaInformation) {
        Tooltip tooltip = new Tooltip(adSchemeMetaInformation.getName()
                                    + ":\n\n" + adSchemeMetaInformation.getDescription());
        Tooltip.install(help,tooltip);
    }

    private void submitHandler() {

        String source = sourceField.getText();
        String amount = amountField.getText();
        String[] selectedPreferences = new String[2];
        String name = adName.getText();
        String description = adDescription.getText();

        for (int i = 0; i < 2; i++) {
            if (checkboxes[i].isSelected())
                selectedPreferences[i] = preferences[i];
        }
        try {
            //AdRepositoryMock.getAdRepositoryMock().addNewAd(new AdvertisementMock(name, preferences[0], Double.parseDouble(amount), 1));
            AdvertisementMetaInformation adMetaInformation = new AdvertisementMetaInformation(name, description, -1, Double.parseDouble(amount));
            IPreferredAd ad = PreferredAdFactory.newPreferredAd(
                    source,
                    AdPreferenceFactory.newPlayViewPreference(),
                    adMetaInformation);

            AdRepository.get().addPreferredAd(ad);
            adProperty.set(ad);

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            showError();
            confirmationText.setText("Advertisement not added");
        }

    }

    public ObjectProperty<IPreferredAd> preferredAdProperty(){
        return this.adProperty;
    }

    private void showError() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Input Error");
        error.setHeaderText("Input invalid");
        error.setContentText("Some input was not valid.\nCorrect your input and try again");
        error.showAndWait();
    }
}
