package communication.connection;

/**
 * A connection for receiving information. This could be from a socket or from any
 * other source. The connection always need to be close when not used any more.
 *
 */
public interface Connection {

    /**
     * Establishes a connection.
     *
     * Used for lazy initialization depending
     * on implementation.
     *
     * @throws Exception
     */
    void connect() throws Exception;

    /**
     * Closes the established connection.
     *
     * Might fail, exceptions are handled internally.
     */
    void close();

}
