package serviceInitiatorService;

import rest.ReSTContainer;

import java.io.IOException;

/**
 * Created by August on 2016-11-29.
 */
public class ServiceInitiatorContainer extends ReSTContainer {

    public ServiceInitiatorContainer(IServiceInitiator initiator, int port) {
        super(port);

        this.createContext("/", new ServiceInitiatorAPI(initiator, this));
    }
}
