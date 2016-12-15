package serviceClient.hostServiceInitiator;

import hostProviderService.HostService;
import serviceClient.openService.OpenService;
import serviceClient.openService.OpenServiceContainer;
import serviceClient.localServiceDirectory.LocalServiceDirectory;
import serviceClient.utilityServices.ContainerServicePair;
import serviceInitiatorService.IServiceInitiator;
import usersService.UserServiceContainer;
import usersService.UsersService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-12-14.
 */
public class HostServiceInitiator implements IServiceInitiator{

    private final LocalServiceDirectory directory;

    private final Map<Integer, String> openServicesIds = new HashMap<>(); // <Port number, ServiceDirectory Id for OpenServiceContainer>

    public HostServiceInitiator(LocalServiceDirectory directory){
        this.directory = directory;
    }

    @Override
    public void initiateServiceContainer(HostService hostServiceInfo) throws IOException {
        int port = hostServiceInfo.port;

        switch(hostServiceInfo.getServiceClassName()){
            case "UsersService":
                closeOpenService(port);
                directory.addService(
                        new ContainerServicePair<UsersService>(
                                port,
                                new UsersService(),
                                (nPort, nService) -> new UserServiceContainer(nService, nPort)
                        )
                );
                break;
            default:
                System.out.println("No such service is available: " + hostServiceInfo.getServiceClassName());
                break;
        }
    }

    public ContainerServicePair<OpenService> addOpenService(int port){
        ContainerServicePair openService = new ContainerServicePair<Object>(
                port,
                new OpenService(),
                (nPort, nService) -> new OpenServiceContainer((int)nPort)
        ).start();

        String serviceId = directory.addService(openService);

        openServicesIds.put(port, serviceId);

        return openService;
    }

    public void closeOpenService(int port){
        directory.removeService(openServicesIds.get(port))
                .getContainer()
                .stop(0);
    }
}
