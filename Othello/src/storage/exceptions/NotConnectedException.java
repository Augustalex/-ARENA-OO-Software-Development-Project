package storage.exceptions;

/**
 * Created by August on 2016-10-27.
 */
public class NotConnectedException extends Exception{

    public NotConnectedException(){
        super("SQLConnection not connected.");
    }
}
