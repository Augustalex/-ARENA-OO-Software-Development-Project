package utilities.callbackLibrary;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by August on 2016-10-10.
 */
public abstract class Callback<T> implements Serializable {

    protected Method method;
    protected T context;
    protected Object[] arguments;

    public abstract void invoke() throws InvocationTargetException, IllegalAccessException;
}
