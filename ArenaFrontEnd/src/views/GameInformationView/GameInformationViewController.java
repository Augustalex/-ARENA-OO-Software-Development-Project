package views.GameInformationView;

import arena.games.gameLauncher.GameLauncher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import arena.games.gameInformation.GameInformation;
import views.FXMLViewController;
import views.DimensionBinder;

import arena.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the GameInformationView.
 *
 * Loads game information into the view from the GameInformation object
 * received as a parameter in the constructor.
 */
public class GameInformationViewController extends FXMLViewController{

    private final GameInformation gameInformation;

    @FXML
    private Rectangle gameImage;

    @FXML
    private Label gameName;

    @FXML
    private Label gameDescription;

    @FXML
    private ScrollPane gameInformationContainer;

    @FXML
    private BorderPane gameInformationContent;

    @FXML
    private VBox gameInformationLeft;

    @FXML
    private VBox gameInformationRight;

    @FXML
    private Button playGameButton;

    @FXML
    private StackPane tournamentListContainer;

    public GameInformationViewController(GameInformation gameInformation){
        this.gameInformation = gameInformation;
    }

    private Parent loadTournamentInformationView() throws IOException {
        return loadFXML(
                "tournament/TournamentInformationView/TournamentInformationView.fxml"
        );
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DimensionBinder.bindWidthToPercentageOfContainer(gameInformationContent, 1, gameInformationContainer);

        DimensionBinder.bindWidthToPercentageOfContainer(gameInformationLeft, 0.7, gameInformationContent);
        DimensionBinder.bindWidthToPercentageOfContainer(gameInformationRight, 0.3, gameInformationContent);

        DimensionBinder.bindWidthToPercentageOfContainer(gameDescription, 0.6, gameInformationLeft);

        DimensionBinder.bindWidthToPercentageOfContainer(playGameButton, 0.4, gameInformationRight);

        gameName.setText(gameInformation.getGameName());
        gameDescription.setText(gameInformation.getGameDescription());

        gameImage.setFill(new ImagePattern(new Image(gameInformation.getImageURL())));
        gameImage.widthProperty().bind(gameInformationContainer.widthProperty().multiply(0.2));
        gameImage.heightProperty().bind(gameInformationContainer.widthProperty().multiply(0.2));

        playGameButton.setOnAction(e -> GameLauncher.launchGame(gameInformation));

        try {
            tournamentListContainer.getChildren().setAll(loadTournamentInformationView());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeView() {

    }
}
