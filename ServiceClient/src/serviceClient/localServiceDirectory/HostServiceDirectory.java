package serviceClient.localServiceDirectory;

import hostProviderService.HostService;
import serviceClient.idService.IdService;
import serviceClient.utilityServices.ContainerServicePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by August on 2016-12-13.
 */
public class HostServiceDirectory {

    private static final IdService nextId = new IdService();

    private Map<String, ContainerServicePair> activeServices = new HashMap<>();

    private Map<String, HostService> services = new HashMap<>();
    /**
     * Adds a HostService into a directory of all services.
     * A String ID is generated for the ContainerServicePair,
     * needed to retrieve it from the directory.
     *
     * The new ID is returned as a String from this method.
     *
     * @param service
     * @return
     */
    public String addActiveService(HostService service){
        String id = service.getServiceClassName() + nextId.getNextId();
        services.put(id, service);
        return id;
    }

    public HostService getService(String serviceId){
        return services.get(serviceId);
    }

    public HostService removeService(String serviceId){
        return services.remove(serviceId);
    }

    public String getObjectId(HostService object){
        return services.entrySet().stream()
                .filter(entry -> entry.getValue().equals(object))
                .map(Map.Entry::getKey)
                .findFirst()
                .get();
    }

    public List<HostService> getAllServices(){
        return services.values().stream().collect(Collectors.toList());
    }

}


