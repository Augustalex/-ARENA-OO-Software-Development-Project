package utilities.router.stateRestorer.exceptions;

/**
 * Created by August on 2016-10-25.
 */
public class UnableToLoadState extends RuntimeException{

    public UnableToLoadState(){
        super("Unable to load state.");
    }
}
