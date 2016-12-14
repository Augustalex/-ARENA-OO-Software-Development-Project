package serviceClient.views.serviceListElement;

import com.google.gson.Gson;
import hostProviderService.Host;
import hostProviderService.HostService;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import serviceClient.portAvailibilityChecker.PortAvailabilityChecker;
import serviceClient.utilityServices.UtilityServicesDirectoryProxy;

import java.io.IOException;

/**
 * Created by August on 2016-12-14.
 */
public class OpenServiceCreationController implements EventHandler<MouseEvent> {

    private final TextField portInput;
    private final Label statusLabel;
    private UtilityServicesDirectoryProxy utilityServices;

    public OpenServiceCreationController(TextField portInput, Label statusLabel){
        this.portInput = portInput;
        this.statusLabel = statusLabel;

        System.out.println("Created new Open Service Creation Controller");
    }

    public OpenServiceCreationController setUtilityServices(UtilityServicesDirectoryProxy utilityServices){
        this.utilityServices = utilityServices;
        return this;
    }

    @Override
    public void handle(MouseEvent event) {

        System.out.println("Creating open service.");

        if(utilityServices == null)
            throw new RuntimeException("Utility Services not set.");

        int port = new PortAvailabilityChecker(portInput.getText())
                .setOutputLabel(statusLabel)
                .getCheckedPort();

        new Thread(() -> {

            HostService hostService = new HostService(
                    "",
                    new Host(utilityServices.getServiceInitiator().getContainer().getAddress().getHostName(), port),
                    new Host(utilityServices.getServiceInitiator().getContainer().getAddress().getHostName(), utilityServices.getServiceInitiator().getContainer().getPort())
            );

            System.out.println(hostService.getURL());
            System.out.println(utilityServices.getHostProvider().getURL());
            try {
                System.out.println("Sending request.");
                Response response = Request.Post(utilityServices.getHostProvider().getURL())
                        .bodyString(new Gson().toJson(hostService), ContentType.APPLICATION_JSON)
                        .execute();

                String id = response.returnContent().asString();

                utilityServices.getServiceInitiator().getService()
                        .addOpenService(port)
                        .serviceStoppedProperty().addListener(e -> {
                    removeAsHost(id);
                });

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }).start();


    }

    private void removeAsHost(String hostProviderServiceId){
        new Thread(() -> {
            try {
                Response response = Request.Delete(utilityServices.getHostProvider().getURL() + "/id/" + hostProviderServiceId)
                        .execute();

                System.out.println(response.returnResponse().getStatusLine());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
