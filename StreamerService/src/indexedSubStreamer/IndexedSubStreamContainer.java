package indexedSubStreamer;

import hostProviderService.Host;
import rest.ReSTContainer;

/**
 * Created by August on 2016-12-16.
 */
public class IndexedSubStreamContainer extends ReSTContainer{

    public IndexedSubStreamContainer(Host hostProviderDetails, int port) {
        super(port);

        createContext("/", new IndexedSubStreamerAPI(hostProviderDetails));
    }

}
