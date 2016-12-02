package hostProviderService;

/**
 * Created by August on 2016-11-29.
 */
public class HostService extends Host {

    private Host serviceInitiator;
    private String serviceClassName;

    public HostService(String serviceClassName, Host serviceHost, Host serviceInitiator) {
        super(serviceHost.address, serviceHost.port);

        this.serviceClassName = serviceClassName;

        this.serviceInitiator = serviceInitiator;
    }

    public String getServiceClassName(){
        return this.serviceClassName;
    }

    public Host getServiceInitiatorHostInformation(){
        return this.serviceInitiator;
    }
}
