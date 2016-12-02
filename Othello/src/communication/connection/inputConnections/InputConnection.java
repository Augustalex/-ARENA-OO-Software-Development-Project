package communication.connection.inputConnections;

import communication.connection.Connection;
import communication.sender.Package;

/**
 * A connection for receiving information. This could be from a socket or from any
 * other source. The connection always need to be close when not used any more.
 *
 * The generic part is for whatever the information received is compiled into. This
 * could be a {@link Package} or a {@link communication.requests.Request} as well as
 * any primitive data type.
 * @param <T>
 */
public interface InputConnection<T> extends Connection {
    /**
     * Receives information from the abstract connection
     * and compiles it to the generically defined Class of
     * T.
     * @return
     * @throws Exception
     */
    T receive() throws Exception;

}
