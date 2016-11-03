package communication.receiver.exceptions;

/**
 * Created by August on 2016-10-29.
 */
public class InvalidPackageException extends RuntimeException {

    public InvalidPackageException(String message){
        super(message);
    }

    public InvalidPackageException(){
        this("Invalid format of message sent. Expected valid Package format.");
    }
}
