package rest;

import java.util.function.Consumer;

/**
 * Much like an ObjectProperty but with the capability of dealing with cancellation.
 *
 * @param <T>
 */
public interface Delivery<T> {

    Delivery<T> onCancel(Runnable callback);

    void cancel();

    Delivery<T> onDelivery(Consumer<T> consumer);

    void deliver(T payload);
}
