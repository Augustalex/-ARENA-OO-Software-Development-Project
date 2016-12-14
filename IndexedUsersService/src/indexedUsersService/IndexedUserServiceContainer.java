package indexedUsersService;

import hostProviderService.Host;
import hostProviderService.HostService;
import rest.ReSTContainer;
import serviceIndexer.ServiceIndexer;

import java.io.IOException;

/**
 * Created by August on 2016-11-29.
 */
public class IndexedUserServiceContainer extends ReSTContainer {

    private final ServiceIndexer indexer;

    public IndexedUserServiceContainer(HostService hostProviderConnectionDetails, int port) {
        super(port);

        indexer = new ServiceIndexer(2, hostProviderConnectionDetails, hostProviderConnectionDetails.getServiceClassName());

        this.createContext("/", new IndexedUserServiceAPI(indexer));
        this.createContext("/id/", new IndexedUserServiceSingleUserAPI(indexer));
    }
}
