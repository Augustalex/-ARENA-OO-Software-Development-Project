package views.configureTournament;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import metaInformation.MetaInformation;
import metaInformation.TournamentMetaInformation;
import tournament.tournamentConfiguration.ITournamentConfiguration;
import tournament.tournamentConfiguration.TournamentConfiguration;
import tournament.tournamentConfiguration.TournamentConfigurationFactory;
import tournament.tournamentStyle.TournamentStyle;
import tournament.tournamentStyle.TreeTournamentStyle;
import utilities.TimeDate;
import views.DimensionBinder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Patric on 2016-11-14.
 */
public class ConfigureTournamentViewController implements Initializable {

    @FXML
    private StackPane createTournamentContainer;

    @FXML
    private VBox createTournamentContent;

    @FXML
    private HBox tournamentHBox;

    @FXML
    private ComboBox tournamentComboBox;

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

        DimensionBinder.bindWidthToPercentageOfContainer(createTournamentContent, 1, createTournamentContainer);

        ITournamentConfiguration configuration = TournamentConfigurationFactory.newTournamentConfiguration();

        buttonTournamentSubmit.setOnAction(e -> {
            if(configuration.isValid())
            System.out.println(configuration.toString());
        });
    }

    private void fillConfigurationWithFormData(ITournamentConfiguration tournamentConfiguration){

        TournamentStyle style;
        TimeDate time = new TimeDate(tournamentDate.getText());

        MetaInformation metaInformation =
                new TournamentMetaInformation()
                        .setStartDate(time)
                        .setName(tournamentName.getText())
                        .setDescription(tournamentDesc.getText());

        ITournamentConfiguration configuration =
                TournamentConfigurationFactory
                        .newTournamentConfiguration()
                        .setTournamentStyle(style)
                        .setMetaInformation((TournamentMetaInformation)metaInformation);

    }
}
