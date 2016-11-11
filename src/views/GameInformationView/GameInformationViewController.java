package views.GameInformationView;

import gameLauncher.GameLauncher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import gameInformation.GameInformation;
import views.FXMLViewController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Simon on 03/11/2016.
 */
public class GameInformationViewController extends FXMLViewController{

    GameInformation gameInformation;

    @FXML
    private Rectangle gameImage;

    @FXML
    private Label gameName;

    @FXML
    private Text gameDescription;

    @FXML
    private BorderPane gameInformationWindow;

    @FXML
    private Button playGameButton;

    public GameInformationViewController(GameInformation gameInformation){
        this.gameInformation = gameInformation;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gameName.setText(gameInformation.getGameName());
        gameDescription.setText(gameInformation.getGameDescription());
        gameImage.setFill(new ImagePattern(new Image(gameInformation.getImageURL())));
        gameImage.widthProperty().bind(gameInformationWindow.widthProperty().multiply(0.2));
        gameImage.heightProperty().bind(gameInformationWindow.widthProperty().multiply(0.2));
        playGameButton.setOnAction(e -> {
            GameLauncher.launchGame(gameInformation);
        });
    }

    @Override
    public void closeView() {

    }
}
