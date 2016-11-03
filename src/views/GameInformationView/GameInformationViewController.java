package views.GameInformationView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.GameInformation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Simon on 03/11/2016.
 */
public class GameInformationViewController implements Initializable{

    GameInformation gameInformation;
    @FXML
    private ImageView gameImage;

    @FXML
    private Label gameName;

    @FXML
    private Text gameDescription;

    public GameInformationViewController(GameInformation gameInformation){
        this.gameInformation = gameInformation;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GameInformationView.fxml"));
        loader.setControllerFactory(c -> {
            return new GameInformationViewController(gameInformation);
        });
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
