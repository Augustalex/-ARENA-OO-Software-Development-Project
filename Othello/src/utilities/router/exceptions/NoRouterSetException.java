package utilities.router.exceptions;

/**
 * Created by August on 2016-10-11.
 */
public class NoRouterSetException extends RuntimeException {

    public NoRouterSetException(){
        super("No utilities.router set. Cannot get application utilities.router.");
    }
}
