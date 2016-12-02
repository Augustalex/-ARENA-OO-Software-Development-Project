package hostProviderService;

import rest.ReSTContainer;

import java.io.IOException;

/**
 * Created by August on 2016-11-29.
 */
public class hostProviderContainer extends ReSTContainer {

    public hostProviderContainer(HostProvider hostProvider, int port) throws IOException {
        super(port);

        this.createContext("/", new HostProviderAPI(hostProvider));
    }

}
