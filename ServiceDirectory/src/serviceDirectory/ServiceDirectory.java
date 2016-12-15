package serviceDirectory;

import hostProviderService.Host;
import hostProviderService.idService.IdService;
import javafx.util.Pair;
import rest.Delivery;
import rest.PropertyDelivery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A directory containing connection details to up and running services.
 *
 * The Services are organized around their Service class name which returns a map
 * pairing each individual service of that type to a unique id.
 *
 * This unique id (together with the service class name) can be used to retrieve a
 * service for use, or to decommission (removing) a service from the directory.
 */
public class ServiceDirectory {

    private IdService nextId = new IdService(0);

    private final Map<String, Map<String, Host>> directory = new HashMap<>();

    public List<Pair<String, Host>> getAll(){
        //TODO might break af
        return directory.entrySet().stream()
                .flatMap(entry -> entry.getValue().entrySet().stream()
                        .map(serviceEntry -> new Pair<>(
                                serviceEntry.getKey(),
                                serviceEntry.getValue()
                        )))
                .collect(Collectors.toList());
    }

    public String publish(String serviceClassName, Host connectionDetails){
        return addNewService(serviceClassName, connectionDetails);
    }

    //TODO implement so that a service is only grabbed once (so that not only one service gets choosen all the time)
    public Delivery<Host> grab(String serviceClassName){
        Delivery<Host> result = new PropertyDelivery<>();

        new Thread(() -> {
            if(directory.containsKey(serviceClassName)){
                System.out.println(directory.get(serviceClassName));
                directory.get(serviceClassName).values().forEach(host -> {
                    System.out.println(host.getURL());
                });

                if(directory.get(serviceClassName).size() > 0)
                    result.deliver(directory.get(serviceClassName).values().stream().findAny().get());
                else
                    result.cancel();
            }
            else
                result.cancel();
        }).start();

        return result;
    }

    public Delivery<Host> get(String serviceClassName, String id){
        Delivery<Host> result = new PropertyDelivery<>();

        new Thread(() -> {
            if(directory.containsKey(serviceClassName)){
                if(directory.get(serviceClassName).containsKey(id))
                    result.deliver(directory.get(serviceClassName).get(id));
                else
                    result.cancel();
            }
            else
                result.cancel();
        }).start();

        return result;
    }

    public void remove(String serviceClassName, String id){
        if(directory.containsKey(serviceClassName)
            && directory.get(serviceClassName).containsKey(id))
                directory.get(serviceClassName).remove(id);
    }

    private void newServiceTypeEntry(String serviceClassName) {
        directory.put(serviceClassName, new HashMap<>());
    }

    private String addNewService(String serviceClassName, Host connectionDetails){
        if(!directory.containsKey(serviceClassName))
            newServiceTypeEntry(serviceClassName);

        String id = String.valueOf(nextId.getNextId());
        directory.get(serviceClassName).put(id, connectionDetails);

        return id;
    }
}
