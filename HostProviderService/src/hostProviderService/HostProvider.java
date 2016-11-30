package hostProviderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by August on 2016-11-29.
 */
public class HostProvider {

    private List<HostService> availableHosts = new ArrayList<>();

    public HostService getNextAvailableHost() throws NoAvailableHosts {
        System.out.println("Getting next available host at index: " + (availableHosts.size()-1));
        if(availableHosts.size() <= 0)
            throw new NoAvailableHosts();
        else
            return availableHosts.remove(0);
    }

    public void addHost(HostService host){
        System.out.println("Adding host: " + host.getURL());
        availableHosts.add(host);
    }
}
