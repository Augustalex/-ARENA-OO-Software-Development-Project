package serviceClient.views.serviceListElement;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import serviceClient.utilityServices.ContainerServicePair;
import serviceClient.views.manageServiceWindow.ManageServiceWindow;

/**
 * Simply an event handler and controller class for a ServiceListElement.
 */
public class ServiceManagerController implements EventHandler<MouseEvent> {

    private final ContainerServicePair containerServicePair;

    public ServiceManagerController(ContainerServicePair containerServicePair){
        this.containerServicePair = containerServicePair;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println("Shit got real!");
        try {
            ManageServiceWindow.startInNewWindow(containerServicePair);
        } catch (Exception e1) {
            System.out.println("Could not load Manage Service Window.");
            e1.printStackTrace();
        }
    }
}
