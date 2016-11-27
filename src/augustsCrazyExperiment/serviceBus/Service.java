package augustsCrazyExperiment.serviceBus;

/**
 * Data structure for wrapping meta information about a Service
 * and necessary information for connecting to the said Service (in this case the URI).
 *
 * Exception for certain methods not supported or other quirks should be mentioned in the service
 * description.
 *
 * Multiple services with identical information may exist as long as the versioning between
 * the services are different.
 */
public class Service {
    public String uri;
    public String host;
    public String name;
    public String description;
    public String version;

    @Override
    public String toString(){
        return "Name: \"" + name +"\", URI: \"" + uri + "\", Host: \"" + host + "\"";
    }
}
