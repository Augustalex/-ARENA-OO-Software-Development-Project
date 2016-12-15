package serviceClient.localServiceDirectory;

import com.google.gson.Gson;
import hostProviderService.Host;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Pair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import serviceClient.idService.IdService;
import serviceClient.utilityServices.ContainerServicePair;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by August on 2016-12-13.
 */
public class LocalServiceDirectory {

    private final IdService nextId = new IdService(0);
    private Map<String, ContainerServicePair> services = new HashMap<>();

    private Map<String, String> onlineServiceIds = new HashMap<>();

    private ObjectProperty<List<ContainerServicePair>> directoryUpdateAvailable = new SimpleObjectProperty<>();
    private Host onlineDirectory;

    public List<ContainerServicePair> getAllServices(){
        return services.values().stream().collect(Collectors.toList());
    }

    public String addService(ContainerServicePair containerServicePair){
        String id = containerServicePair.service.getClass().getSimpleName() + "#" + nextId.getNextId();
        services.put(id, containerServicePair);
        addOnline(containerServicePair);

        directoryUpdateAvailableProperty().set(getAllServices());

        System.out.println("Added service " + containerServicePair.getService().getClass().getSimpleName());
        //Remove service from directory when service is stopped
        containerServicePair.serviceStoppedProperty().addListener((observable, oldValue, newValue) -> {
            removeService(id);
        });

        return id;
    }

    public ContainerServicePair getService(String id){
        return services.get(id);
    }

    public ContainerServicePair removeService(String id){
        ContainerServicePair servicePair = services.remove(id);
        removeOnline(id);
        directoryUpdateAvailableProperty().set(getAllServices());

        return servicePair;
    }

    public String getId(ContainerServicePair containerServicePair){
        return services.entrySet().stream()
                .filter(entry -> entry.getValue().equals(containerServicePair))
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
    }

    public ObjectProperty<List<ContainerServicePair>> directoryUpdateAvailableProperty(){
        return this.directoryUpdateAvailable;
    }

    public LocalServiceDirectory setOnlineDirectoryDetails(Host connectionDetails){
        this.onlineDirectory = connectionDetails;
        return this;
    }

    private void addOnline(ContainerServicePair containerServicePair){
        if(this.onlineDirectory != null){
            try {

                Pair<String, Host> data = new Pair<>(
                        containerServicePair.getService().getClass().getSimpleName(),
                        new Host(
                                containerServicePair.getContainer().getLocalAddress(),
                                containerServicePair.getContainer().getPort()
                        )
                );

                Response response = Request.Post(onlineDirectory.getURL())
                        .bodyString(
                            new Gson().toJson(data),
                            ContentType.APPLICATION_JSON
                        ).execute();

                System.out.println(response.returnResponse());
            } catch (IOException e) {
                System.out.println("Could not send data to online service directory.");
                e.printStackTrace();
            }
        }
    }

    private void removeOnline(String localServiceId){
        if(this.onlineDirectory == null)
            return;

        try{
            ContainerServicePair containerServicePair = getService(localServiceId);

            String baseURL = new Host(
                    containerServicePair.getContainer().getLocalAddress(),
                    containerServicePair.getContainer().getPort()
            ).getURL();

            String finalUrl = baseURL + "/id/"
                    + containerServicePair.getService().getClass().getSimpleName()
                    + onlineServiceIds.get(localServiceId);

            Response response = Request.Delete(finalUrl)
                    .execute();

            System.out.println(response.returnResponse().getStatusLine());
        } catch (IOException e) {
            System.out.println("Could not remove service from online directory.");
            e.printStackTrace();
        }
    }
}
