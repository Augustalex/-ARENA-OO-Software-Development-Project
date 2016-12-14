package serviceClient.utilityServices;

import hostProviderService.Host;
import hostProviderService.HostProvider;
import hostProviderService.HostProviderContainer;
import serviceClient.hostServiceInitiator.HostServiceInitiator;
import serviceClient.serviceDirectory.ServiceDirectory;
import serviceInitiatorService.IServiceInitiator;
import serviceInitiatorService.ServiceInitiator;
import serviceInitiatorService.ServiceInitiatorContainer;

import java.io.IOException;

/**
 * Contains factory methods for instantiating and creating a
 * new service and its respective http ReST Container.
 */
public class UtilityServiceFactory {

    public static Host newHostProviderDetails(int port) throws IOException {

        Host connectionDetails = new Host("192.168.1.4", port);
        return connectionDetails;
    }

    public static ContainerServicePair newHostProvider(int port) throws IOException{
        return new ContainerServicePair<>(port, new HostProvider(), (nPort, nService) -> new HostProviderContainer(nService, nPort));

    }

    public static ContainerServicePair<HostServiceInitiator> newServiceInitiator(int port, ServiceDirectory directory) throws IOException {
        return new ContainerServicePair<HostServiceInitiator>(port, new HostServiceInitiator(directory), (nPort, nService) -> new ServiceInitiatorContainer(nService, nPort));
    }
}
