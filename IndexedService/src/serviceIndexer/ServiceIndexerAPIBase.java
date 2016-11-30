package serviceIndexer;

import rest.ReST;
import services.IService;

/**
 * Created by August on 2016-11-29.
 */
public abstract class ServiceIndexerAPIBase extends ReST {

    private IServiceIndexer serviceIndexer;

    public ServiceIndexerAPIBase(IServiceIndexer serviceIndexer){
        this.serviceIndexer = serviceIndexer;
    }

    public IServiceIndexer getServiceIndexer(){
        return this.serviceIndexer;
    }
}
