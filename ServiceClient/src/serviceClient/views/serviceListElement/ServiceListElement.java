package serviceClient.views.serviceListElement;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import serviceClient.utilityServices.ContainerServicePair;
import serviceClient.views.manageServiceWindow.ManageServiceWindow;

/**
 * A list element that extends a regular Button, but
 * contains a ContainerServicePair.
 */
public class ServiceListElement extends Button {

    public ServiceListElement(String name){
        this.setText(name);
    }

    public ServiceListElement(String name, EventHandler<MouseEvent> eventHandler){
        this(name);
        attachEventHandler(eventHandler);
    }

    public void attachEventHandler(EventHandler<MouseEvent> eventHandler){
        this.setOnMouseClicked(eventHandler);
    }
}