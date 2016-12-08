package arena.session.exceptions;

/**
 * Created by August on 2016-12-08.
 */
public class NotLoggedInException extends RuntimeException {

    public NotLoggedInException(){
        super("Cannot access function as user is not logged in.");
    }
}
