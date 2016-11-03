package views.MainWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import tests.RunMatch;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    @FXML
    private Pane contentView;

    @FXML
    private HBox tabView;

    @FXML
    private Button playButton;

    @FXML
    private Button watchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Parent loadFXML(String fxmlViewPath) throws IOException {
        return FXMLLoader.load(getClass().getResource("/Views/" + fxmlViewPath));
    }
}
