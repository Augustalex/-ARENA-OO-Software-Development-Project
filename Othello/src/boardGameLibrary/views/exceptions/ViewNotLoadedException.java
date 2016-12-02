package boardGameLibrary.views.exceptions;

/**
 * Created by August on 2016-10-10.
 */
public class ViewNotLoadedException extends RuntimeException {

    public ViewNotLoadedException(){
        super("View is not loaded. Cannot use view at this time.");
    }
}
