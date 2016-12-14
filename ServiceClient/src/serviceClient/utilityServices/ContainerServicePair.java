package serviceClient.utilityServices;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Callback;
import rest.ReSTContainer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Contains two classes correlated to each other.
 * The first one is a container and httpServer for the
 * second parameter which is the service run in the
 * container.
 */
public class ContainerServicePair<T> {

    private final BiFunction<Integer, T, ReSTContainer> containerFactory;

    public ReSTContainer container = null;
    public T service = null;

    int port = 0;
    public ServerSocket portReservation = null;

    private BooleanProperty serviceStoppedProperty = new SimpleBooleanProperty(false);

    public ContainerServicePair(int port, T service, BiFunction<Integer, T, ReSTContainer> containerFactory){
        this.containerFactory = containerFactory;
        this.service = service;
        this.port = port;
        reservePort(port);
    }

    public BooleanProperty serviceStoppedProperty(){
        return this.serviceStoppedProperty;
    }

    public ContainerServicePair start(){
        freePort();
        this.container = containerFactory.apply(port, service);
        this.container.start();
        return this;
    }

    public ReSTContainer getContainer(){
        if(this.container == null)
            throw new RuntimeException("Container not constructed or factory not set.");
        else
            return this.container;
    }

    public T getService(){
        return this.service;
    }

    private void reservePort(int port){
        try {
            this.portReservation = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not reserve port " + port);
        }
    }

    private void freePort(){
            try {
                if(this.portReservation != null)
                    this.portReservation.close();
            } catch (IOException e) {
                System.out.println("Could not close port reservation.");
            }
    }


}
