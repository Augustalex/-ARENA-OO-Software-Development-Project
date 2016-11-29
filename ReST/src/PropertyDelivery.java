import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;

import javax.security.auth.callback.Callback;
import java.util.function.Consumer;

/**
 * Implements the Delivery interface with the use of Java Properties.
 *
 * The actual payload class for the Delivery is not specified and is set
 * generically.
 * @param <T>
 */
public class PropertyDelivery<T> implements Delivery<T> {

    private Runnable onCancel = () -> System.out.println("No callback on Cancel set.");
    private ObjectProperty<T> payload = new SimpleObjectProperty<>(null);

    private Consumer<T> onDelivery = t -> {
        System.out.println("No consumer set.");
    };

    @Override
    public void onCancel(Runnable callback) {
        this.onCancel = callback;
    }

    /**
     * Cancels the instance of Delivery.
     */
    @Override
    public void cancel() {
        this.onCancel.run();
    }

    @Override
    public void onDelivery(Consumer<T> consumer){
        this.onDelivery = consumer;
    }

    /**
     * Sets the payload for the instance of Delivery. Activates all listeners.
     * @param payload
     */
    @Override
    public void deliver(T payload) {
        this.onDelivery.accept(payload);
    }

}
