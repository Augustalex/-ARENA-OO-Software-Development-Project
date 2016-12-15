package serviceClient.views.servicesView;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import serviceClient.localServiceDirectory.LocalServiceDirectory;
import serviceClient.utilityServices.UtilityServicesDirectoryProxy;
import serviceClient.views.addServiceWindow.AddServiceWindow;
import serviceClient.views.serviceListElement.ServiceListElement;
import serviceClient.views.serviceListElement.ServiceManagerController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-12-13.
 */
public class ServicesViewController implements Initializable{

    private final LocalServiceDirectory directory;
    private final UtilityServicesDirectoryProxy utilityServices;

    @FXML
    private Button addNewServiceButton;

    @FXML
    private VBox runningServicesList;

    public ServicesViewController(LocalServiceDirectory directory, UtilityServicesDirectoryProxy utilityServices){
        this.utilityServices = utilityServices;
        this.directory = directory;
    }

    private void setupDirectoryUpdateListener() {
        directory.directoryUpdateAvailableProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                System.out.println("Here again!");
                this.runningServicesList.getChildren().clear();
                displayAllServices(runningServicesList, directory);
            });
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupDirectoryUpdateListener();
        captureAddNewServiceButtonActions();
        displayAllServices(runningServicesList, directory);
    }

    private void displayAllServices(VBox runningServicesList, LocalServiceDirectory directory) {
        directory.getAllServices().stream()
                .map(pair -> new ServiceListElement(directory.getId(pair), new ServiceManagerController(pair)))
                .forEach(listElement -> runningServicesList.getChildren().add(listElement));
    }

    private void captureAddNewServiceButtonActions(){
        addNewServiceButton.setOnMouseClicked(e -> {
            AddServiceWindow.startAsNewWindow(directory, utilityServices);
        });
    }

}
