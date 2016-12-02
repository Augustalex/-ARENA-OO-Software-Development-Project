package serviceIndexer;

import rest.ReST;
import rest.ReSTContainer;
import serviceIndexer.count.ServiceIndexerCountAPI;
import serviceIndexer.index.ServiceIndexerIndexAPI;

import java.io.IOException;

/**
 * Created by August on 2016-11-29.
 */
public class ServiceIndexerContainer extends ReSTContainer{

    public ServiceIndexerContainer(IServiceIndexer serviceIndexer, int port) throws IOException {
        super(port);

        this.createContext("/", new ServiceIndexerAPI(serviceIndexer));
        this.createContext("/count", new ServiceIndexerCountAPI(serviceIndexer));
        this.createContext("/index/", new ServiceIndexerIndexAPI(serviceIndexer));
    }
    
}
