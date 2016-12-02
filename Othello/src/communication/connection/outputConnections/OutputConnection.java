package communication.connection.outputConnections;

import communication.connection.Connection;

import java.io.IOException;

/**
 * Extends the Connection interface and adds the ability to send objects
 * of a generic type through the connection.
 * @param <T>
 */
public interface OutputConnection<T> extends Connection {

    /**
     * Transmits a object T through the connection.
     * @param object
     */
    void transmit(T object) throws IOException;
}
