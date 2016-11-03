package views.PlayView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import tests.RunMatch;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-11-02.
 */
public class PlayViewController implements Initializable{

    @FXML
    private BorderPane playViewWindow;

    @FXML
    private Button playMatchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playMatchButton.setOnAction(e -> {
            RunMatch.runMatch(playViewWindow);
        });
    }
}
