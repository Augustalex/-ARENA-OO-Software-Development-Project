package utilities.router.paneRouter.exceptions;

/**
 * Created by August on 2016-10-19.
 */
public class NoContainerPaneSetException extends RuntimeException {

    public NoContainerPaneSetException(){
        super("No containing Pane set on View. Cannot return a container Pane.");
    }
}
