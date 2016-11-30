package serviceIndexer;

import hostProviderService.Host;
import hostProviderService.HostService;

import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by August on 2016-11-29.
 */
public interface IServiceIndexer {

    HostService getServiceConnectionDetails(int index) throws ServiceNotFoundException;

    void scaleUp(int numberOfInstances, Consumer<Boolean> callback);

    List<HostService> getAllServicesConnectionDetails();

    int serviceCount();

    int getPartitionCapacity();

    HostService hostNewService() throws HostConnectionFailure;

}
