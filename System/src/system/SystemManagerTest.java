package system;

import hostProviderService.*;
import indexedUsersService.IndexedUserServiceContainer;
import serviceInitiatorService.ServiceInitiatorContainer;

import java.io.IOException;
import java.net.Inet4Address;

/**
 * Created by August on 2016-11-29.
 */
public class SystemManagerTest {

    private hostProviderContainer hostProviderContainer;
    private HostProvider hostProvider;

    public static void main(String[] args) {
        SystemManagerTest system = new SystemManagerTest();
        system.initSystem();
    }

    public void initSystem(){
        try {
            hostProvider = new HostProvider();
            hostProviderContainer = new hostProviderContainer(hostProvider, 2000);
            hostProviderContainer.start();
            startBasicSystems();
        } catch (Exception e) {
            System.out.println("Could not start Host Provider or Basic Systems.");
            e.printStackTrace();
        }
    }

    public void startBasicSystems() throws NoAvailableHosts, IOException {

        //hostProvider.addHost(new HostService("", new Host(machineIP, 2005), new Host(machineIP, 2006)));
        //hostProvider.addHost(new HostService("", new Host(machineIP, 2007), new Host(machineIP, 2008)));

        String machineIP = Inet4Address.getLocalHost().getHostAddress().toString();
        ServiceInitiatorContainer initiatorContainer_2005 = new ServiceInitiatorContainer(2006);
        initiatorContainer_2005.start();
        ServiceInitiatorContainer initiatorContainer_2006 = new ServiceInitiatorContainer(2008);
        initiatorContainer_2006.start();

        IndexedUserServiceContainer indexedUserServiceContainer = new IndexedUserServiceContainer(
            new HostService("",
                    new Host(
                            machineIP,
                    2000
                    ),
                    new Host(
                            machineIP,
                            2001
                    )
            ),
            2002
        );

        indexedUserServiceContainer.start();

    }
}
