package serviceClient.views.addServiceWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import serviceClient.serviceDirectory.ServiceDirectory;
import serviceClient.utilityServices.UtilityServicesDirectoryProxy;

/**
 * Created by August on 2016-12-14.
 */
public class AddServiceWindow extends Application {

    private final ServiceDirectory serviceDirectory;
    private UtilityServicesDirectoryProxy utilityServices;

    public AddServiceWindow(ServiceDirectory serviceDirectory, UtilityServicesDirectoryProxy utilityServices){
        this.serviceDirectory = serviceDirectory;
        this.utilityServices = utilityServices;
    }

    public static void startAsNewWindow(ServiceDirectory serviceDirectory, UtilityServicesDirectoryProxy utilityServices){
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Add new Service");

        AddServiceWindow addServiceWindow = new AddServiceWindow(serviceDirectory, utilityServices);

        try {
            addServiceWindow.start(stage);
        } catch (Exception e) {
            System.out.println("Could not open Add New Service Window");
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Inside");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/serviceClient/views/addServiceWindow/AddServiceView.fxml"));
        loader.setControllerFactory(c -> new AddServiceViewController(serviceDirectory, utilityServices));
        Pane pane = loader.load();
        primaryStage.setScene(new Scene(pane, 300,550));
        primaryStage.show();
        System.out.println("on stage.");
    }
}
