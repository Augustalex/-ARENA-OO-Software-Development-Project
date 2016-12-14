package serviceClient.views.manageServiceWindow;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import serviceClient.utilityServices.ContainerServicePair;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-12-13.
 */
public class ManageServiceViewController implements Initializable {

    private final ContainerServicePair containerServicePair;

    @FXML
    private Label nameLabel;

    @FXML
    private Label portLabel;

    @FXML
    private Button stopButton;

    @FXML
    private Button showLogButton;

    @FXML
    private Button hostsListButton;

    public ManageServiceViewController(ContainerServicePair containerServicePair){
        this.containerServicePair = containerServicePair;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupServiceDetailLabels();
        setupStopButtonActions();
    }

    private void setupStopButtonActions() {
        stopButton.setOnMouseClicked(e -> {
            new Thread(() -> {
                containerServicePair.getContainer().stop(1);
                System.out.println("Stopped service.");
                Platform.runLater(() -> containerServicePair.serviceStoppedProperty().set(true));
            }).start();
        });
    }

    private void setupServiceDetailLabels() {
        try {
            portLabel.setText(String.valueOf(containerServicePair.getContainer().getAddress().getPort()));
            nameLabel.setText(containerServicePair.service.getClass().getSimpleName());
        }
        catch(NumberFormatException ex){
            System.out.println("Port number must be only integers.");
        }
    }


}
