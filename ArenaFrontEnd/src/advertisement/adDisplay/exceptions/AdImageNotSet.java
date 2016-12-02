package advertisement.adDisplay.exceptions;

/**
 * Exception thrown when an ad image is not set on the object
 * trying to manipulate or get data from it.
 */
public class AdImageNotSet extends RuntimeException {

    public AdImageNotSet(){
        super("Cannot adjust dimensions when ad image not set.");
    }
}
