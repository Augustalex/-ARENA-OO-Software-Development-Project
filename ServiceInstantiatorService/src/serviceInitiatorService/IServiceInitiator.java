package serviceInitiatorService;

import hostProviderService.HostService;

import java.io.IOException;

/**
 * Created by August on 2016-12-14.
 */
public interface IServiceInitiator {

    void initiateServiceContainer(HostService hostServiceInfo) throws IOException;

}
