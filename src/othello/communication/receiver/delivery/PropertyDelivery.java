package communication.receiver.delivery;

import communication.OneTimeChangeListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;

/**
 * Implements the Delivery interface with the use of Java Properties.
 *
 * The actual payload class for the Delivery is not specified and is set
 * generically.
 * @param <T>
 */
public class PropertyDelivery<T> implements Delivery<T> {

    private BooleanProperty canceled = new SimpleBooleanProperty(false);
    private ObjectProperty<T> payload = new SimpleObjectProperty<>(null);

    /**
     * Set a {@link OneTimeChangeListener} for the event of delivery cancellation.
     *
     * @param onCancelListener
     */
    public void onCancel(OneTimeChangeListener<Boolean> onCancelListener){
        this.canceled.addListener(onCancelListener);
    }

    /**
     * Returns true if the instance is already canceled.
     * @return
     */
    public boolean isCanceled(){
        return this.canceled.get();
    }

    /**
     * Cancels the instance of Delivery.
     */
    @Override
    public void cancel() {
        this.canceled.set(true);
    }

    /**
     * Sets a {@link OneTimeChangeListener} for when the payload is delivered.
     * @param onDeliveryListener
     */
    @Override
    public void onDelivery(OneTimeChangeListener<T> onDeliveryListener){
        this.payload.addListener(onDeliveryListener);
    }

    /**
     * Returns true if the instance has a payload set.
     * @return
     */
    @Override
    public boolean hasPayload(){
        return this.payload.get() != null;
    }

    /**
     * Sets the payload for the instance of Delivery. Activates all listeners.
     * @param payload
     */
    @Override
    public void deliver(T payload) {
        this.payload.set(payload);
    }

    /**
     * Returns the set payload of the instance. If the payload is not set, the return value
     * is null.
     * @return
     */
    @Override
    public T getPayload(){
        if(this.hasPayload())
            return this.payload.get();
        else
            return null;
    }
}
