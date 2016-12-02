package communication;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 * A decorator for the InvalidationListener interface that removes itself from
 * the Observables list of listeners, once the invalidated method is fired.
 *
 */
public class OneTimeInvalidationListener implements InvalidationListener {

    private InvalidationListener invalidationListener;

    public OneTimeInvalidationListener(InvalidationListener invalidationListener){
        this.invalidationListener = invalidationListener;
    }

    @Override
    public void invalidated(Observable observable) {
        this.invalidationListener.invalidated(observable);
        observable.removeListener(this);
    }
}
