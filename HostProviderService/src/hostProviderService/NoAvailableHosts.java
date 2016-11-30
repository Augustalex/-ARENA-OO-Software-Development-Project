package hostProviderService;

/**
 * Thrown when there are no available host for a new service instance.
 */
public class NoAvailableHosts extends Exception {

    public NoAvailableHosts(){
        super("There are currently no available hosts.");
    }

}
