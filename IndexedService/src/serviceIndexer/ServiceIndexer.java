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
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
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

    private Host hostProvider;
    private String serviceClassName;

    private Queue<Integer> reusableObjectIds = new LinkedBlockingQueue<>();
    private Map<Integer, HostService> serviceHosts = new HashMap<>();

    public ServiceIndexer(int partitionCapacity, Host hostProviderConnectionDetails, String serviceClassName){
        this.partitionCapacity = partitionCapacity;
        this.hostProvider = hostProviderConnectionDetails;
        this.serviceClassName = serviceClassName;
    }

    @Override
    public Delivery<HostService> getServiceConnectionDetails(int lookupIndex) {
        Delivery<HostService> serviceDetails = new PropertyDelivery<>();

        if(lookupIndex >= this.currentCapacity)
            this.scaleUp(1, wasSuccessful -> {
                if(wasSuccessful){
                    System.out.println("Scaled successfully.");
                    serviceDetails.deliver(serviceHosts.get(getServiceIndex(lookupIndex)));
                }
                else{
                    System.out.println("Scale up was unsuccessful.");
                    serviceDetails.deliver(null);
                }
            });
        else
            serviceDetails.deliver(serviceHosts.get(getServiceIndex(lookupIndex)));

        return serviceDetails;
    }

    @Override
    public void scaleUp(int numberOfInstances, Consumer<Boolean> callback) {
        Delivery<Boolean> status = new PropertyDelivery<>();
        status.onDelivery(callback);

        //TODO bug! The status callback should only execute when ALL instances are created. Some kind of "countdown" promise need to be created.
        IntStream.range(0, numberOfInstances)
                .forEach(i -> addService(status));
    }

    @Override
    public List<HostService> getAllServicesConnectionDetails() {
        System.out.println("getAllServicesConnectionDetails [ServiceIndexer]");
        serviceHosts.values().forEach(c -> System.out.print(c.getURL() + ", "));
        return serviceHosts
                .values().stream()
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
    public HostService getNewHost() throws HostConnectionFailure {
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
        if(this.reusableObjectIds.size() > 0)
            return this.reusableObjectIds.poll();
        else
            return this.nextObjectId++;
    }

    @Override
    public void recycleObjectId(int id) {
        this.reusableObjectIds.offer(id);
    }

    private void addService(Delivery<Boolean> status){
        new Thread(() -> {
            int index = serviceCount();
            try {
                System.out.println("Hosting new Service.");
                HostService newService = getNewHost();

                serviceHosts.put(index, newService);
                currentCapacity += partitionCapacity;

                //TODO make the "UsersService" "magic string" an Enum or some static variable of this class (or one that can be set).
                //Alert the new host about what service they should host
                System.out.println("Sending post request to Hosts ServiceInitiatorService");
                    Request.Post(newService.getServiceInitiatorHostInformation().getURL())
                            .bodyString(gson.toJson(new HostService(serviceClassName, new Host(newService.address, newService.port), newService.getServiceInitiatorHostInformation())), ContentType.APPLICATION_JSON)
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
