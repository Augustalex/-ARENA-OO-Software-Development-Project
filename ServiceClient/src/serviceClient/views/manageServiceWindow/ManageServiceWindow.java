package serviceClient.views.manageServiceWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import serviceClient.utilityServices.ContainerServicePair;

/**
 * Created by August on 2016-12-13.
 */
public class ManageServiceWindow extends Application {

    private final ContainerServicePair containerServicePair;

    public ManageServiceWindow(ContainerServicePair containerServicePair){
        this.containerServicePair = containerServicePair;
    }

    public static void startInNewWindow(ContainerServicePair containerServicePair) throws Exception {
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Manage Service");

        ManageServiceWindow window = new ManageServiceWindow(containerServicePair);
        window.start(stage);

        containerServicePair.serviceStoppedProperty().addListener((observable, oldValue, newValue) -> {
            stage.close();
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        int width = 300;
        int height = 550;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/serviceClient/views/ManageServiceWindow/ManageServiceView.fxml"));
        loader.setControllerFactory(c -> new ManageServiceViewController(containerServicePair));
        Pane pane = loader.load();
        stage.setScene(new Scene(pane, width, height));

        stage.show();
    }

}
