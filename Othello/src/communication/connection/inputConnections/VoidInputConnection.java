package communication.connection.inputConnections;

/**
 * A null object for when another InputConnection failed to instantiate.
 */
public class VoidInputConnection<T> implements InputConnection<T> {
    @Override
    public void connect() throws Exception {

    }

    @Override
    public T receive() {
        return null;
    }

    @Override
    public void close() {
        System.out.println("Trying to close void connection.");
    }
}
