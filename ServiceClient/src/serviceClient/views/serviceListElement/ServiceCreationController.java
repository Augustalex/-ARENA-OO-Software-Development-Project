package serviceClient.views.serviceListElement;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import rest.ReSTContainer;
import serviceClient.localServiceDirectory.LocalServiceDirectory;
import serviceClient.utilityServices.ContainerServicePair;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Created by August on 2016-12-14.
 */
public class ServiceCreationController<T> implements EventHandler<MouseEvent> {

    private final TextField portInput;
    private final Label statusLabel;

    private BiFunction<Integer, T, ReSTContainer> containerFactory = null;
    private LocalServiceDirectory directory = null;
    private Supplier<T> serviceFactory = (Supplier<T>)(() -> null);

    public ServiceCreationController(TextField portInput, Label statusLabel){
        this.portInput = portInput;
        this.statusLabel = statusLabel;

    }

    public ServiceCreationController setServiceDirectory(LocalServiceDirectory directory){
        this.directory = directory;
        return this;
    }

    public ServiceCreationController setContainerFactory(BiFunction<Integer, T, ReSTContainer> containerFactory){
        this.containerFactory = containerFactory;
        return this;
    }

    public ServiceCreationController setServiceFactory(Supplier<T> serviceFactory){
        this.serviceFactory = serviceFactory;
        return this;
    }

    @Override
    public void handle(MouseEvent event) {
        try{
            int port = Integer.parseInt(portInput.getText());

            try{
                ServerSocket server = new ServerSocket(port);
                statusLabel.setText("Port available");
                statusLabel.getStyleClass().add("available");
                server.close();

                if(containerFactory == null)
                    throw new RuntimeException("No ContainerFactory set");
                else
                    createNewService(port);

            } catch (IOException e) {
                System.out.println("Port already taken.");
                statusLabel.setText("Port " + port + " not available.");
                statusLabel.getStyleClass().add("unavailable");
            }
        }
        catch(NumberFormatException ex){
            System.out.println("Port has to be only integers.");
        }
    }

    private void createNewService(int port) {
        if(directory == null)
            throw new RuntimeException("Service directory not set, cannot create new Service.");
        else
            directory.addService(
                    new ContainerServicePair<T>(port, serviceFactory.get(), containerFactory)
                            .start()
            );
    }
}
