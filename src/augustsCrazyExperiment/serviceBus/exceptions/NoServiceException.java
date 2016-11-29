package augustsCrazyExperiment.serviceBus.exceptions;

/**
 * Exception for when there is no available service for given request.
 */
public class NoServiceException extends RuntimeException {

    public NoServiceException(String uri){
        super("There is no Service corresponding to the URI: " + uri);
    }
}
