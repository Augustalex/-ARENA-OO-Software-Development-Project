package serviceClient.serviceManager;

import rest.ReSTContainer;

import java.util.Map;

/**
 * Created by August on 2016-12-12.
 */
public interface IServiceManager {

    Map<String, ReSTContainer> getServices();
}
