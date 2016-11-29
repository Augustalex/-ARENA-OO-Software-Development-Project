package rest;

import java.util.function.Consumer;

/**
 * Much like an ObjectProperty but with the capability of dealing with cancellation.
 *
 * @param <T>
 */
public interface Delivery<T> {

    void onCancel(Runnable callback);

    void cancel();

    void onDelivery(Consumer<T> consumer);

    void deliver(T payload);
}
