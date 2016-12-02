package augustsCrazyExperiment.serviceBus;

import java.util.List;

/**
 *  Interface for retrieving a list of all available services to the OfflineServiceBus (or other entity).
 */
public interface ServiceDirectory {

    List<Service> getServices();

}
