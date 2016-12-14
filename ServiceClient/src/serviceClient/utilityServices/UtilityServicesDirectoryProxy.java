package serviceClient.utilityServices;

import hostProviderService.Host;
import hostProviderService.HostService;
import serviceClient.hostServiceInitiator.HostServiceInitiator;
import serviceInitiatorService.ServiceInitiator;

/**
 * Created by August on 2016-12-13.
 */
public class UtilityServicesDirectoryProxy {

    public ContainerServicePair<HostServiceInitiator> serviceInitiator;
    public Host hostProvider;

    public UtilityServicesDirectoryProxy setHostProviderDetails(Host details){
        this.hostProvider = details;
        return this;
    }

    public UtilityServicesDirectoryProxy setServiceInitiator(ContainerServicePair<HostServiceInitiator> initiator){
        this.serviceInitiator = initiator;
        return this;
    }

    public Host getHostProvider(){
        if(hostProvider == null)
            throw new RuntimeException("No HostProvider set.");
        else
            return hostProvider;
    }

    public ContainerServicePair<HostServiceInitiator> getServiceInitiator(){
        if(serviceInitiator == null)
            throw new RuntimeException("No ServiceInitiator set.");
        else
            return serviceInitiator;
    }
}
