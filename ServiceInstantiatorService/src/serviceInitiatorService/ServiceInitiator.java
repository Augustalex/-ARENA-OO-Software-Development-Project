package serviceInitiatorService;

import hostProviderService.HostService;
import subStream.SubStream;
import subStream.SubStreamContainer;
import usersService.UserServiceContainer;
import usersService.UsersService;

import java.io.IOException;

/**
 * Created by August on 2016-11-29.
 */
public class ServiceInitiator implements IServiceInitiator{

    //TODO implement so that it can terminate the current running service and start a new one.
    public void initiateServiceContainer(HostService hostServiceInfo) throws IOException {
        System.out.println("Hello " + hostServiceInfo.getServiceClassName());
        switch(hostServiceInfo.getServiceClassName()){
            case "UsersService":
                new UserServiceContainer(new UsersService(), hostServiceInfo.port).start();
                break;
            //TODO might break here....
            case "SubStream":
                new SubStreamContainer(new SubStream(hostServiceInfo.port+1), hostServiceInfo.port).start();
                break;
            default:
                System.out.println("No such service: " + hostServiceInfo.getServiceClassName());
                break;
        }
    }
}
