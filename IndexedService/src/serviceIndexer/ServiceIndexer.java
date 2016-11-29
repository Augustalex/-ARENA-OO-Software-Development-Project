package serviceIndexer;

import indexedService.IIndexedService;
import indexedService.IndexedService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import rest.Delivery;
import rest.PropertyDelivery;

import javax.management.ServiceNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by August on 2016-11-29.
 */
public abstract class ServiceIndexer implements IServiceIndexer {

    private final int partitionCapacity;
    private int currentCapacity;

    private Map<Integer, IIndexedService> services = new HashMap<>();

    public ServiceIndexer(int partitionCapacity){
        this.partitionCapacity = partitionCapacity;
    }

    @Override
    public IIndexedService getService(int lookupIndex) throws ServiceNotFoundException {
        final int serviceIndex = getServiceIndex(lookupIndex);

        if(services.containsKey(serviceIndex))
            return services.get(serviceIndex);
        else
            throw new ServiceNotFoundException();
    }

    @Override
    public Delivery<Boolean> scaleUp(int numberOfInstances) {
        Delivery<Boolean> status = new PropertyDelivery<>();

        IntStream.range(0, numberOfInstances)
                .forEach(i -> addService(status));

        return status;
    }

    @Override
    public IndexedService[] getAllServices() {
        return (IndexedService[]) services
                .values()
                .stream()
                .toArray(IIndexedService[]::new);
    }

    @Override
    public int serviceCount() {
        return (int)Math.ceil(currentCapacity/partitionCapacity);
    }

    private void addService(Delivery<Boolean> status){
        int index = serviceCount() + 1;

        try {
            IIndexedService newService = newService();
            services.put(index, newService);
            currentCapacity += partitionCapacity;
            status.deliver(true);
        }
        catch(Exception e){
            status.deliver(false);
        }
    }

    private int getServiceIndex(int lookupIndex){
        return (int)Math.ceil(lookupIndex/partitionCapacity);
    }

}
