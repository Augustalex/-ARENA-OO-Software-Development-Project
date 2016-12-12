package systemsManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-12-12.
 */
public class SystemsManagerViewController implements Initializable {

    @FXML
    private Label machineIPLabel;

    @FXML
    private VBox runningServicesList;

    @FXML
    private TextField portNumberInput;

    @FXML
    private VBox predefinedServicesList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
