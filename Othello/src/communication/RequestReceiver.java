package communication;

import communication.connection.inputConnections.InputConnection;
import communication.receiver.PropertyReceiver;
import communication.receiver.delivery.Delivery;
import communication.requests.Request;

/**
 * Handles received request packages.
 *
 * The requests are delivered asynchronously via the
 * {@link Delivery} class.
 */
public class RequestReceiver extends PropertyReceiver<Request[]> {

    public RequestReceiver(InputConnection<Request[]> connection) {
        super(connection);
    }

    /**
     * Returns a {@link Delivery} that can be used to retrieve the requests
     * once they are delivered.
     * @param connection
     * @return
     */
    public Delivery expectRequest(InputConnection connection){
        return expectDelivery();
    }

}
