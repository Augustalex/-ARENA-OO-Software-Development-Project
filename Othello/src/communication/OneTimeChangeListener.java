package communication;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * A decorator for the ChangeListener interface that removes itself from
 * the Observables list of listeners, once the changed method is fired.
 * @param <T>
 */
public class OneTimeChangeListener<T> implements ChangeListener<T>{

    private ChangeListener<T> changeListener;

    public OneTimeChangeListener(ChangeListener<T> changeListener){
        this.changeListener = changeListener;
    }

    public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
        this.changeListener.changed(observable, oldValue, newValue);
        observable.removeListener(this);
    }
}
