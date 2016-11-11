package views.GameInformationView;

import gameLauncher.GameLauncher;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import gameInformation.GameInformation;
import views.FXMLViewController;
import views.DimensionBinder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Simon on 03/11/2016.
 */
public class GameInformationViewController extends FXMLViewController{

    private GameInformation gameInformation;

    @FXML
    private Rectangle gameImage;

    @FXML
    private Label gameName;

    @FXML
    private Text gameDescription;

    @FXML
    private ScrollPane gameInformationContainer;

    @FXML
    private BorderPane gameInformationContent;

    @FXML
    private Button playGameButton;

    @FXML
    private StackPane tournamentListContainer;

    public GameInformationViewController(GameInformation gameInformation){
        this.gameInformation = gameInformation;
    }

    private Parent loadTournamentInformationView() throws IOException {
        return loadFXML(
                "TournamentInformationView/TournamentInformationView.fxml"
        );
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DimensionBinder.bindWidthToPercentageOfContainer(gameInformationContent, 0.95, gameInformationContainer);


        gameName.setText(gameInformation.getGameName());
        gameDescription.setText(gameInformation.getGameDescription());

        gameImage.setFill(new ImagePattern(new Image(gameInformation.getImageURL())));
        gameImage.widthProperty().bind(gameInformationContainer.widthProperty().multiply(0.2));
        gameImage.heightProperty().bind(gameInformationContainer.widthProperty().multiply(0.2));

        playGameButton.setOnAction(e -> {
            GameLauncher.launchGame(gameInformation);
        });

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
