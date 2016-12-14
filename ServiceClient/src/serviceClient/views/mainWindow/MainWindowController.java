package serviceClient.views.mainWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import serviceClient.serviceDirectory.ServiceDirectory;
import serviceClient.views.servicesView.ServicesViewController;
import serviceClient.views.setupView.SetupViewController;
import serviceClient.serviceDirectory.HostServiceDirectory;
import serviceClient.utilityServices.UtilityServiceFactory;
import serviceClient.utilityServices.UtilityServicesDirectoryProxy;
import serviceClient.views.machineView.MachineViewController;
import serviceClient.views.tabView.RouterButton;
import serviceClient.views.tabView.RouterButtonBuilder;
import serviceClient.views.tabView.TabViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-12-12.
 */
public class MainWindowController implements Initializable{

    private final ServiceDirectory serviceDirectory;
    private final UtilityServicesDirectoryProxy utilityProxy;
    @FXML
    private BorderPane mainWindow;

    @FXML
    private StackPane contentView;

    public MainWindowController() throws IOException {

        this.serviceDirectory = new ServiceDirectory();
        serviceDirectory.addService(UtilityServiceFactory.newHostProvider(2000).start());
        this.utilityProxy = new UtilityServicesDirectoryProxy()
                                    .setHostProviderDetails(UtilityServiceFactory.newHostProviderDetails(2000))
                                    .setServiceInitiator(UtilityServiceFactory.newServiceInitiator(2001, serviceDirectory).start());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("hello");

        setupMenuTabs();

    }

    private void setupMenuTabs(){
        try{
            Pane menu = loadMenu();
            mainWindow.setTop(menu);
        } catch (IOException e) {
            System.out.println("Could not load menu tabs.");
            e.printStackTrace();
        }
    }

    private Pane loadMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../tabView/TabView.fxml"));
        loader.setControllerFactory(c -> new TabViewController(contentView, getMenuButtons()));
        return loader.load();
    }

    private RouterButton[] getMenuButtons(){
        return new RouterButton[]{
            new RouterButtonBuilder()
                    .buttonText("Machine")
                    .fxmlPath("../machineView/MachineView.fxml")
                    .contentView(contentView)
                    .controllerFactory(c -> new MachineViewController(utilityProxy))
                    .build(),
            new RouterButtonBuilder()
                    .buttonText("Services")
                    .fxmlPath("/serviceClient/views/servicesView/ServicesView.fxml")
                    .contentView(contentView)
                    .controllerFactory(c -> new ServicesViewController(serviceDirectory, utilityProxy))
                .build(),
            new RouterButtonBuilder()
                    .buttonText("Setup")
                    .fxmlPath("/serviceClient/views/setupView/SetupView.fxml")
                    .contentView(contentView)
                    .controllerFactory(c -> new SetupViewController(utilityProxy, serviceDirectory))
                .build()
        };
    }
}
