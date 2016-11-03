package utilities.HistoryTracker.exceptions;

/**
 * Created by August on 2016-10-10.
 */
public class NoHistoryException extends RuntimeException {

    public NoHistoryException(){
        super("Cannot get history when there are no stored history.");
    }
}
