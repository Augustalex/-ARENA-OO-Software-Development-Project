package hostProviderService;

import hostProviderService.idService.IdService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by August on 2016-11-29.
 */
public class HostProvider {

    private IdService nextId = new IdService(0);

    private Map<String, HostService> hosts = new HashMap<>();

    private List<HostService> availableHosts = new ArrayList<>();

    public HostService getNextAvailableHost() throws NoAvailableHosts {
        if(hosts.size() <= 0)
            throw new NoAvailableHosts();
        else {
            Map.Entry<String, HostService> firstEntry = hosts.entrySet().stream().findFirst().get();
            hosts.remove(firstEntry.getKey());
            return firstEntry.getValue();
        }
    }

    public String addHost(HostService host){
        System.out.println("Adding host: " + host.getURL());
        String id = String.valueOf(nextId.getNextId());
        hosts.put(id, host);

        printHosts();
        return id;
    }

    public void removeHost(String id){
        hosts.remove(id);
        printHosts();
    }

    public void printHosts(){
        System.out.println("\nAll Hosts");
        hosts.entrySet().forEach(entry -> {
            System.out.println("id:" + entry.getKey() + "\thost:\"" + entry.getValue().getURL() + "\"");
        });
        System.out.println("\n");
    }
}

