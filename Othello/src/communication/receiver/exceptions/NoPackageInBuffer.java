package communication.receiver.exceptions;

/**
 * Created by August on 2016-10-27.
 */
public class NoPackageInBuffer extends RuntimeException {

    public NoPackageInBuffer(){
        super("No package in buffer. Cannot pull from buffer.");
    }
}
