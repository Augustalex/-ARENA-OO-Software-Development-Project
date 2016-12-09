package views.handleAdvertisement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import views.FXMLViewController;
import views.handleAdvertisement.handleExistingAdvertisements.HandleAdvertisementMainViewController;
import views.handleAdvertisement.handleNewAdvertisement.HandleAdvertisementController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Johan on 2016-12-08.
 */
public class HandleAdvertisementContainerController extends FXMLViewController implements Initializable {
    @FXML
    private VBox mainAdvertisementContainer;

    @Override
    public void closeView() {
        //If threads are started, do cleanup here
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAdMainPage();
    }

    private void loadNewAdPAge() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/handleAdvertisement/handleNewAdvertisement/HandleAdvertisement.fxml"));
            Parent parent = loader.load();
            HandleAdvertisementController controller = loader.getController();
            controller.preferredAdProperty().addListener((observable, oldValue, newValue) -> {
                loadAdMainPage();
            });
            mainAdvertisementContainer.getChildren().setAll(parent);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private void loadAdMainPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/handleAdvertisement/handleExistingAdvertisements/HandleAdvertisementMain.fxml"));
            Parent parent = loader.load();
            HandleAdvertisementMainViewController controller = loader.getController();
            controller.getButton().addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    loadNewAdPAge();
                }
            });
            mainAdvertisementContainer.getChildren().setAll(parent);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
