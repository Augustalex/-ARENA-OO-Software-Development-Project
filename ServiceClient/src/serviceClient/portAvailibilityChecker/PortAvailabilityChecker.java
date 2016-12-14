package serviceClient.portAvailibilityChecker;

import javafx.scene.control.Label;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Checks if a port is available by trying to start a ServerSocket
 * on the given port number.
 */
public class PortAvailabilityChecker {

    private final int port;
    private Label statusLabel;

    public PortAvailabilityChecker(String port){
        int portInteger = 0; //Default port value if error in parsing port String.

        try{
            portInteger = Integer.parseInt(port);
        }
        catch(NumberFormatException ex){
            System.out.println("Port must consist of only integers.");
        }
        finally {
            this.port = portInteger;
        }
    }

    public int getCheckedPort(){
        return check().getPort();
    }

    public int getPort(){
        return this.port;
    }

    public PortAvailabilityChecker check(){
        try {
            ServerSocket server = new ServerSocket(port);
            server.close();
            postStatus("Port available");
        } catch (IOException e) {
            System.out.println("Port " + port + " not available.");
            postStatus("Port not available");
        }

        return this;
    }

    public PortAvailabilityChecker setOutputLabel(Label statusLabel){
        this.statusLabel = statusLabel;
        return this;
    }

    private void postStatus(String message){
        if(statusLabel != null)
            statusLabel.setText(message);
    }
}
