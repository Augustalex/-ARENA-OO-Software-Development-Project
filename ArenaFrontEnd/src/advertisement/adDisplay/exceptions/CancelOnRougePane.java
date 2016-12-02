package advertisement.adDisplay.exceptions;

/**
 * Is thrown when user is trying to set loop to close
 * on stage close, when the Pane is not added to a Stage yet.
 */
public class CancelOnRougePane extends RuntimeException {

    public CancelOnRougePane(){
        super("Trying to set stage close, when pane is not added to any Stage yet.");
    }
}
