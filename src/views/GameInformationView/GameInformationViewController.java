package views.GameInformationView;

import gameLauncher.GameLauncher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.GameInformation;
import model.OthelloGameInformation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Simon on 03/11/2016.
 */
public class GameInformationViewController implements Initializable{

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
}
