package arena.games.gameServer;

import arena.IPInformation.IPInformation;
import javafx.beans.property.ObjectProperty;

/**
 * An interface for running a IGame Server, as well as retaining active ip information
 * about the server (so that other clients may connect to it).
 */
public interface GameServer {

    /**
     * Returns active arena.IPInformation.
     *
     * This information may change when relaunching the GameServer.
     * @return
     */
    ObjectProperty<IPInformation> getIPInformationProperty();

    void launch();
}
