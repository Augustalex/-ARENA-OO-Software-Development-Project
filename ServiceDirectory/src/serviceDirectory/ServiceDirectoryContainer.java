package serviceDirectory;

import rest.ReSTContainer;

/**
 * A Container for the Service Directory service.
 *
 * Has two context. One for posting new services and grabbing all available services.
 * The second one is for single operations. Either grabbing all services available for
 * a certain kind of service or simply getting a specific service by its id.
 * You may also from this second context, delete specific services.
 *
 * Example URI's:
 *
 * To grab all: "/"
 * To grab all of a service type: "/id/UsersService/"
 * To get a specific running service: "/id/UsersService/10324"
 */
public class ServiceDirectoryContainer extends ReSTContainer {

    public ServiceDirectoryContainer(ServiceDirectory serviceDirectory, int port) {
        super(port);

        createContext("/", new ServiceDirectoryAPI(serviceDirectory));
        createContext("/id/", new ServiceDirectorySingleAPI(serviceDirectory));
    }
}
