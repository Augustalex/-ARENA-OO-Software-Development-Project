package serviceClient.views.addServiceWindow;

import hostProviderService.HostService;
import indexedUsersService.IndexedUserServiceContainer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import serviceClient.serviceDirectory.ServiceDirectory;
import serviceClient.utilityServices.UtilityServicesDirectoryProxy;
import serviceClient.views.serviceListElement.OpenServiceCreationController;
import serviceClient.views.serviceListElement.ServiceCreationController;
import serviceClient.views.serviceListElement.ServiceListElement;
import usersService.UserServiceContainer;
import usersService.UsersService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-12-14.
 */
public class AddServiceViewController implements Initializable {

    private final ServiceDirectory serviceDirectory;
    private UtilityServicesDirectoryProxy utilityServices;

    @FXML
    private TextField portInput;

    @FXML
    private Label statusLabel;

    @FXML
    private VBox servicesList;


    public AddServiceViewController(ServiceDirectory serviceDirectory, UtilityServicesDirectoryProxy utilityServices){
        this.serviceDirectory = serviceDirectory;
        this.utilityServices = utilityServices;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        servicesList.getChildren().add(
                new ServiceListElement(
                    "Open Service",
                        new OpenServiceCreationController(portInput, statusLabel)
                            .setUtilityServices(utilityServices)
                )
        );

        servicesList.getChildren().add(
                new ServiceListElement(
                        "UsersService",
                        new ServiceCreationController<UsersService>(portInput, statusLabel)
                                .setServiceDirectory(serviceDirectory)
                                .setServiceFactory(UsersService::new)
                                .setContainerFactory((port, service) -> new UserServiceContainer((UsersService)service, (int)port))
                )
        );

        servicesList.getChildren().add(
                new ServiceListElement(
                        "IndexedUsersService",
                        new ServiceCreationController<UsersService>(portInput, statusLabel)
                                .setServiceDirectory(serviceDirectory)
                                .setServiceFactory(UsersService::new)
                                .setContainerFactory((port, service) -> new IndexedUserServiceContainer(
                                            new HostService(
                                                    "UsersService",
                                                    utilityServices.getHostProvider(),
                                                    null),
                                            (int)port)
                                )
                )
        );
    }
}
