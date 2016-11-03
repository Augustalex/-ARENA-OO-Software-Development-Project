package utilities.callbackLibrary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by August on 2016-10-15.
 */
public class ArrayCallback<T> extends Callback<T> {

    public ArrayCallback(Method method, T context, Object[] arguments){
        this.method = method;
        this.context = context;
        this.arguments = arguments;

    }
    @Override
    public void invoke() throws InvocationTargetException, IllegalAccessException {
        this.method.invoke(this.context, this.arguments);
    }
}
