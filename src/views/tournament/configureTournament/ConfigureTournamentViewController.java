package views.tournament.configureTournament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import metaInformation.MetaInformation;
import metaInformation.TournamentMetaInformation;
import tournament.tournamentConfiguration.ITournamentConfiguration;
import tournament.tournamentConfiguration.TournamentConfigurationFactory;
import tournament.tournamentStyle.ITournamentStyle;
import tournament.tournamentStyle.TournamentStyleFactory;
import utilities.TimeDate;
import views.DimensionBinder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * View controller for the ConfigureTournamentView.
 *
 * Validates form data and submits a new ITournamentConfiguration for review on action.
 *
 */
public class ConfigureTournamentViewController implements Initializable {

    @FXML
    private StackPane createTournamentContainer;

    @FXML
    private VBox createTournamentContent;

    @FXML
    private HBox tournamentHBox;

    @FXML
    private ComboBox<ITournamentStyle> tournamentComboBox;

    @FXML
    private TextField tournamentName;

    @FXML
    private TextArea tournamentDesc;

    @FXML
    private TextField tournamentDate;

    @FXML
    private Button buttonTournamentSubmit;

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

        TimeDate time = new TimeDate(tournamentDate.getText());

        MetaInformation metaInformation =
                new TournamentMetaInformation()
                        .setStartDate(time)
                        .setName(tournamentName.getText())
                        .setDescription(tournamentDesc.getText());

        return(
                TournamentConfigurationFactory
                        .newTournamentConfiguration()
                        .setTournamentStyle(tournamentComboBox.getValue())
                        .setMetaInformation((TournamentMetaInformation)metaInformation)
        );
    }
}
