package serviceIndexer;

import com.google.gson.Gson;
import hostProviderService.Host;
import hostProviderService.HostService;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import rest.Delivery;
import rest.PropertyDelivery;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Manages and creates new Service Instances that are all
 * partitioned according to a capacity set at construction of the ServiceIndexer.
 *
 */
public class ServiceIndexer implements IServiceIndexer {

    private int nextObjectId = 0;

    private Gson gson = new Gson();

    private final int partitionCapacity;
    private int currentCapacity = 0;

    private String hostProviderURL;
    private HostService hostProvider;

    private Map<Integer, HostService> services = new HashMap<>();

    public ServiceIndexer(int partitionCapacity, HostService hostProviderConnectionDetails){
        this.partitionCapacity = partitionCapacity;

        this.hostProviderURL = hostProviderConnectionDetails.getURL();
        this.hostProvider = hostProviderConnectionDetails;
    }

    @Override
    public Delivery<HostService> getServiceConnectionDetails(int lookupIndex) {
        Delivery<HostService> serviceDetails = new PropertyDelivery<>();

        if(lookupIndex >= this.currentCapacity)
            this.scaleUp(1, wasSuccessful -> {
                if(wasSuccessful){
                    System.out.println("Scaled successfully.");
                    serviceDetails.deliver(services.get(getServiceIndex(lookupIndex)));
                }
                else{
                    System.out.println("Scale up was unsuccessful.");
                    serviceDetails.deliver(null);
                }
            });
        else
            serviceDetails.deliver(services.get(getServiceIndex(lookupIndex)));

        return serviceDetails;
    }

    @Override
    public void scaleUp(int numberOfInstances, Consumer<Boolean> callback) {
        Delivery<Boolean> status = new PropertyDelivery<>();
        status.onDelivery(callback);

        IntStream.range(0, numberOfInstances)
                .forEach(i -> addService(status));

    }

    @Override
    public List<HostService> getAllServicesConnectionDetails() {
        System.out.println("getAllServicesConnectionDetails [ServiceIndexer]");
        services.values().forEach(c -> System.out.print(c.getURL() + ", "));
        return services
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public int serviceCount() {
        return (int)Math.ceil(currentCapacity/partitionCapacity);
    }

    @Override
    public int getPartitionCapacity() {
        return this.partitionCapacity;
    }

    @Override
    public HostService hostNewService() throws HostConnectionFailure {
        //TODO make async, put in thread
        try{
            return gson.fromJson(Request.Get(this.hostProvider.getURL())
                    .execute().returnContent().toString(), HostService.class);
        } catch (IOException e) {
            throw new HostConnectionFailure("Could not host new service.");
        }
    }

    @Override
    public int getNextObjectId(){
        return this.nextObjectId++;
    }

    private void addService(Delivery<Boolean> status){
        new Thread(() -> {
            int index = serviceCount();
            System.out.println("Hello from the new thread.");
            try {
                System.out.println("Hosting new Service.");
                HostService newService = hostNewService();
                System.out.println("Got new host information.");
                services.put(index, newService);
                currentCapacity += partitionCapacity;

                //TODO make the "UsersService" "magic string" an Enum or some static variable of this class (or one that can be set).
                //Alert the new host about what service they should host
                System.out.println("Sending post request to Hosts ServiceInitiatorService");
                    Request.Post(newService.getServiceInitiatorHostInformation().getURL())
                            .bodyString(gson.toJson(new HostService("UsersService", new Host(newService.address, newService.port), newService.getServiceInitiatorHostInformation())), ContentType.APPLICATION_JSON)
                            .execute();

                System.out.println("New hosted service is set up.");
                status.deliver(true);
            }
            catch(Exception e){
                status.deliver(false);
            }
        }).start();
    }

    private int getServiceIndex(int lookupIndex){
        return (int)Math.ceil(lookupIndex/partitionCapacity);
    }


}
