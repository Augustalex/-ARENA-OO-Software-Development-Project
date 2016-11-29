package advertisement.exceptions;

/**
 * Exception that is raised when trying to switch to next Ad in
 * queue, when the queue is empty.
 */
public class NoAdsInQueue extends RuntimeException {

    public NoAdsInQueue(){
        super("No ads in queue. Cannot display next ad.");
    }
}
