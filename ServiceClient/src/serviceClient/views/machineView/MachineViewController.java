package serviceClient.views.machineView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import serviceClient.utilityServices.UtilityServicesDirectoryProxy;

import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-12-12.
 */
public class MachineViewController implements Initializable {

    private final UtilityServicesDirectoryProxy utilityProxy;

    @FXML
    private Label machineIP;

    @FXML
    private VBox servicesList;

    public MachineViewController(UtilityServicesDirectoryProxy utilityProxy){
        this.utilityProxy = utilityProxy;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            machineIP.setText(Inet4Address.getLocalHost().getHostAddress());

            Label hostProviderLabel = new Label(
                    "HostProvider: " +
                            utilityProxy.getHostProvider().getURL());

            Label serviceInitiatorLabel = new Label(
                    "ServiceInitiator: " +
                            utilityProxy.getServiceInitiator().getContainer().getAddress().getHostName() +
                            ":" + utilityProxy.getServiceInitiator().getContainer().getAddress().getPort());

            servicesList.getChildren().addAll(hostProviderLabel, serviceInitiatorLabel);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
