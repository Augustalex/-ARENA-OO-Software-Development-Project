package communication.receiver;

import communication.OneTimeChangeListener;
import communication.connection.inputConnections.InputConnection;
import communication.connection.inputConnections.VoidInputConnection;
import communication.receiver.delivery.Delivery;
import communication.receiver.delivery.PropertyDelivery;
import communication.receiver.exceptions.NoPackageInBuffer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles receives from a connection with Java Properties.
 * @param <T>
 */
public abstract class PropertyReceiver<T> implements Receiver{

    private InputConnection<T> connection;

    /**
     * Notifies when an element is pushed onto the buffer.
     */
    private final ObjectProperty<Void> receivedPackageNotifier = new SimpleObjectProperty<>(null);

    private final Object bufferKey = new Object();
    private final List<T> buffer = new ArrayList<>();

    public PropertyReceiver(InputConnection<T> connection){
        this.connection = connection;
        this.start();
    }

    /**
     * Returns a delivery that can be used to plan action before
     * anything is really available.
     *
     * @return
     */
    @Override
    public Delivery<T> expectDelivery(){
        Delivery<T> delivery = new PropertyDelivery<>();
        requestDelivery(delivery);

        return delivery;
    }

    /**
     * Starts a connection and the waitAndReceive method.
     * @return
     */
    private PropertyReceiver start(){
        try {
            this.connection.connect();
            waitAndReceive();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Could not requestConnection. InputConnection not set");

            this.connection = new VoidInputConnection<>();
        }

        return this;
    }

    /**
     * Returns an element from the buffer.
     * @return
     */
    private T popBuffer(){
        synchronized (bufferKey) {
            if (this.buffer.size() <= 0)
                throw new NoPackageInBuffer();
            else
                return this.buffer.get(this.buffer.size());
        }
    }

    /**
     * Pushes an element to the buffer.
     * @param payload
     */
    private void pushToBuffer(T payload){
        synchronized (bufferKey){
            this.buffer.add(payload);
            this.receivedPackageNotifier.set(null);
        }
    }

    /**
     * Continuously waits for incoming elements on the
     * {@link InputConnection} and pushes them onto the buffer.
     */
    private void waitAndReceive(){
        new Thread(() -> {
            try {
                T payload = null;
                payload = this.connection.receive();
                pushToBuffer(payload);
                waitAndReceive();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Requests an element for a delivery.
     * @param delivery
     */
    private void requestDelivery(Delivery<T> delivery){
        this.receivedPackageNotifier.addListener(new OneTimeChangeListener<>((observable, oldValue, newValue) -> {
            delivery.deliver(this.popBuffer());
        }));
    }

}
