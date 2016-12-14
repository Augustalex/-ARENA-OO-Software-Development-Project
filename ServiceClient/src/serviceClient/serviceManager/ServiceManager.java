package serviceClient.serviceManager;

import rest.ReSTContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-12-12.
 */
public class ServiceManager implements IServiceManager{

    private Map<String, ReSTContainer> services = new HashMap<>();

    @Override
    public Map<String, ReSTContainer> getServices(){
        return services;
    }
}
