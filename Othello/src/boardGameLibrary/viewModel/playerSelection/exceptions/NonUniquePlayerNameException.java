package boardGameLibrary.viewModel.playerSelection.exceptions;

/**
 * Created by August on 2016-10-23.
 */
public class NonUniquePlayerNameException extends RuntimeException {

    public NonUniquePlayerNameException(){
        super("There are multiple players by that name. Cannot instantiate new player object.");
    }
}
