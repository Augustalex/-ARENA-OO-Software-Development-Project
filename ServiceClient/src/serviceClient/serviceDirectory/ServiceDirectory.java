package serviceClient.serviceDirectory;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import serviceClient.idService.IdService;
import serviceClient.utilityServices.ContainerServicePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by August on 2016-12-13.
 */
public class ServiceDirectory {

    private final IdService nextId = new IdService(0);

    private Map<String, ContainerServicePair> services = new HashMap<>();

    private ObjectProperty<List<ContainerServicePair>> directoryUpdateAvailable = new SimpleObjectProperty<>();

    public List<ContainerServicePair> getAllServices(){
        return services.values().stream().collect(Collectors.toList());
    }

    public String addService(ContainerServicePair containerServicePair){
        String id = containerServicePair.service.getClass().getSimpleName() + "#" + nextId.getNextId();
        services.put(id, containerServicePair);

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
}
