package boardGameLibrary.boardGame.match.exceptions;

/**
 * Created by August on 2016-10-26.
 */
public class NoSnapshotSetException extends RuntimeException{

    public NoSnapshotSetException(){
        super("No snapshot set. Cannot retrieve snapshot.");
    }
}
