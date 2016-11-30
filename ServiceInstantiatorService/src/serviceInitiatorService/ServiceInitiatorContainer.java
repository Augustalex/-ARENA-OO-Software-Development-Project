package serviceInitiatorService;

import rest.ReSTContainer;

import java.io.IOException;

/**
 * Created by August on 2016-11-29.
 */
public class ServiceInitiatorContainer extends ReSTContainer {

    public ServiceInitiatorContainer(int port) throws IOException {
        super(port);

        this.createContext("/", new ServiceInitiatorAPI(new ServiceInitiatorService(), this));
    }
}
