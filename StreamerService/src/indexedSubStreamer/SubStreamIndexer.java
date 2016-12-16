package indexedSubStreamer;

import com.google.gson.Gson;
import hostProviderService.Host;
import hostProviderService.HostService;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import rest.Delivery;
import rest.PropertyDelivery;
import serviceIndexer.HostConnectionFailure;
import serviceIndexer.IServiceIndexer;
import serviceIndexer.ServiceIndexer;

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
 * Created by August on 2016-12-16.
 */
public class SubStreamIndexer implements IServiceIndexer{

    private int nextObjectId = 0;

    private Gson gson = new Gson();

    private final int partitionCapacity;
    private int currentCapacity = 0;

    private Host hostProvider;
    private String serviceClassName;

    private Queue<Integer> reusableObjectIds = new LinkedBlockingQueue<>();
    private Map<Integer, HostService> serviceHosts = new HashMap<>();
    private Host liveStreamDetails;

    public SubStreamIndexer(int partitionCapacity, Host hostProviderConnectionDetails, String serviceClassName) {
        this.partitionCapacity = partitionCapacity;
        this.hostProvider = hostProviderConnectionDetails;
        this.serviceClassName = serviceClassName;

    }

    public SubStreamIndexer setLiveStreamDetails(Host liveStreamDetails){
        this.liveStreamDetails = liveStreamDetails;
        return this;
    }

    @Override
    public Delivery<HostService> getServiceConnectionDetails(int lookupIndex) {
        Delivery<HostService> serviceDetails = new PropertyDelivery<>();

        System.out.println("Getting sub stream connection details.");
        if(lookupIndex >= this.currentCapacity)
            this.scaleUp(1, wasSuccessful -> {
                if(wasSuccessful){
                    System.out.println("Scaled successfully.");
                    try {
                        HostService service = serviceHosts.get(getServiceIndex(lookupIndex));

                        Response response = Request.Get(service.getURL()).execute();

                        HttpResponse responseContent = response.returnResponse();
                        String body = EntityUtils.toString(responseContent.getEntity());
                        Host liveStreamConnectionDetails = new Gson().fromJson(body, Host.class);
                        serviceDetails.deliver(new HostService("SubStream", liveStreamConnectionDetails, service.getServiceInitiatorHostInformation()));
                    } catch (IOException e) {
                        System.out.println("Could not send request to sub stream.");
                        e.printStackTrace();
                    }
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

    public void addService(Delivery<Boolean> status){
        new Thread(() -> {
            int index = serviceCount();
            try {
                System.out.println("Hosting new Service.");
                HostService newService = getNewHost();

                System.out.println("Got new host " + newService.getURL());
                serviceHosts.put(index, newService);
                currentCapacity += partitionCapacity;

                //TODO make the "UsersService" "magic string" an Enum or some static variable of this class (or one that can be set).
                //Alert the new host about what service they should host
                System.out.println("Sending post request to Hosts ServiceInitiatorService: " + newService.getServiceInitiatorHostInformation().getURL());

                String output = gson.toJson(new HostService(serviceClassName, new Host(newService.address, newService.port), newService.getServiceInitiatorHostInformation()));
                System.out.println(output);
                Response response = Request.Post(newService.getServiceInitiatorHostInformation().getURL())
                        .bodyString(output, ContentType.APPLICATION_JSON)
                        .execute();

                System.out.println("Got response: " + response.returnResponse().getStatusLine().toString());

                //Send information to SubStream with details how to connect to liveStream input.
                System.out.println("Sending live stream details to sub stream service.");
                if(this.liveStreamDetails != null) {
                    Response response2 = Request.Post(newService.getURL())
                            .bodyString(new Gson().toJson(liveStreamDetails), ContentType.APPLICATION_JSON)
                            .execute();

                    System.out.println(response2.returnResponse().getStatusLine().toString());
                }

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
