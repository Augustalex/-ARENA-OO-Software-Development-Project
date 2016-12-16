package serviceClient.hostServiceInitiator;

import hostProviderService.HostService;
import serviceClient.openService.OpenService;
import serviceClient.openService.OpenServiceContainer;
import serviceClient.localServiceDirectory.LocalServiceDirectory;
import serviceClient.utilityServices.ContainerServicePair;
import serviceInitiatorService.IServiceInitiator;
import subStream.SubStream;
import subStream.SubStreamContainer;
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
        System.out.println("Initiating service on Service Initiator.");

        int port = hostServiceInfo.port;

        switch(hostServiceInfo.getServiceClassName()){
            case "UsersService":
                closeOpenService(port);
                directory.addService(
                        new ContainerServicePair<UsersService>(
                                port,
                                new UsersService(),
                                (nPort, nService) -> new UserServiceContainer(nService, nPort)
                        ).start()
                );
                break;
            case "SubStream":
                closeOpenService(port);
                directory.addService(
                        new ContainerServicePair<SubStream>(
                                port,
                                new SubStream(hostServiceInfo.port+1),
                                (nPort, subStream) -> new SubStreamContainer((SubStream)subStream, (int)nPort)
                        ).start()
                );

                System.out.println("Added SubStream service at port " + port + " and " + (port+1));
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
        System.out.println("Deleting open service at port " + port);
        directory.removeService(openServicesIds.get(port))
                .getContainer()
                .stop(0);
    }
}
