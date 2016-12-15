package hostProviderService;

import rest.ReSTContainer;

import java.io.IOException;

/**
 * A HTTP Server and HostProvider service container.
 */
public class HostProviderContainer extends ReSTContainer {

    public HostProviderContainer(HostProvider hostProvider, int port) {
        super(port);

        System.out.println("New host provider container! on port " + port);
        this.createContext("/", new HostProviderAPI(hostProvider));
        this.createContext("/id/", new HostProviderSingleAPI(hostProvider));
    }

}
