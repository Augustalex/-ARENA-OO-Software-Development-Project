package communication.receiver.delivery;

import communication.OneTimeChangeListener;
import javafx.beans.value.ChangeListener;

/**
 * Created by August on 2016-10-27.
 */
public interface Delivery<T> {

    void onCancel(OneTimeChangeListener<Boolean> onCancelListener);

    boolean isCanceled();

    void cancel();

    void onDelivery(OneTimeChangeListener<T> onDeliveryListener);

    boolean hasPayload();

    void deliver(T payload);

    T getPayload();
}
