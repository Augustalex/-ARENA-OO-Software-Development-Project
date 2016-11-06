package advertisement.adDisplay.exceptions;

/**
 * Created by August on 2016-11-05.
 */
public class AdImageNotSet extends RuntimeException {

    public AdImageNotSet(){
        super("Cannot adjust dimensions when ad image not set.");
    }
}
