package boardGameLibrary.viewModel.playerSelection.exceptions;

/**
 * Created by August on 2016-10-23.
 */
public class NoSuchPlayerException extends RuntimeException{

    public NoSuchPlayerException(){
        super("There is no such player by that name. Cannot instantiate new player object.");
    }
}
