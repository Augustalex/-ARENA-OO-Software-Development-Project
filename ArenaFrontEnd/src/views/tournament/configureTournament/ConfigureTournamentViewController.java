package views.tournament.configureTournament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import arena.metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import arena.metaInformation.tournamentMetaInformation.TournamentMetaInformation;
import arena.tournament.tournamentConfiguration.ITournamentConfiguration;
import arena.tournament.tournamentConfiguration.TournamentConfigurationFactory;
import arena.tournament.tournamentStyle.ITournamentStyle;
import arena.tournament.tournamentStyle.TournamentStyleFactory;
import arena.timeDate.TimeDate;
import views.DimensionBinder;
import views.FXMLViewController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * View controller for the ConfigureTournamentView.
 *
 * Validates form data and submits a new ITournamentConfiguration for review on action.
 *
 */
public class ConfigureTournamentViewController extends FXMLViewController implements Initializable {

    @FXML
    private StackPane createTournamentContainer;

    @FXML
    private VBox createTournamentContent;

    @FXML
    private VBox tournamentHBox;

    @FXML
    private ComboBox<ITournamentStyle> tournamentComboBox;

    @FXML
    private TextField tournamentName;

    @FXML
    private TextArea tournamentDesc;

    @FXML
    private DatePicker tournamentDate;

    @FXML
    private Button buttonTournamentSubmit;

    @FXML
    private TextField hour;

    @FXML
    private TextField minutes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<ITournamentStyle> styles = FXCollections.observableArrayList(TournamentStyleFactory.getTournamentStyles());
        tournamentComboBox.getItems().setAll(styles);

        DimensionBinder.bindWidthToPercentageOfContainer(createTournamentContent, 0.6, createTournamentContainer);

        buttonTournamentSubmit.setOnAction(e -> {
            ITournamentConfiguration configuration = this.newConfigurationFromFormData();
            if(configuration.isValid())
                configuration.submit();
            else
                System.out.println("All fields are not filled in correctly!");
        });
    }

    /**
     * Takes all input from the view objects and puts the in a new TournamentConfiguration object.
     *
     * This object is not guaranteed to be valid, and some fields may be empty.
     * @return a new ITournamentConfiguration
     */
    private ITournamentConfiguration newConfigurationFromFormData(){

        String timeString = " " + hour.getText() + ":" + minutes.getText();
        TimeDate time = new TimeDate(tournamentDate.getValue().toString() + timeString);

        TournamentMetaInformation metaInformation =
                (TournamentMetaInformation) new TournamentMetaInformation()
                       .setStartDate(time)
                       .setName(tournamentName.getText())
                       .setDescription(tournamentDesc.getText());

        return(
                TournamentConfigurationFactory
                        .newTournamentConfiguration()
                        .setTournamentStyle(tournamentComboBox.getValue())
                        .setMetaInformation((ITournamentMetaInformation) metaInformation)
        );
    }

    @Override
    public void closeView() {

    }
}
