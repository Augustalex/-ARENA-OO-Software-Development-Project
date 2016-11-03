package utilities.callbackLibrary;

/**
 * Created by August on 2016-10-13.
 */
public interface CallbackMethod<T extends Callback> {

    void setCallback(T callback);

}
