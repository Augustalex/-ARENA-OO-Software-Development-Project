package boardGameLibrary.players.exceptions;

/**
 * Created by August on 2016-10-15.
 */
public class CellClickObserverNotAssignedException extends RuntimeException {

    public CellClickObserverNotAssignedException(){
        super("Cannot call Local Player makeMove without setting a CellClickObserver first.");
    }
}
