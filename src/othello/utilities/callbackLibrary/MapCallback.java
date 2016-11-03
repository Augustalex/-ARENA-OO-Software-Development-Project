package utilities.callbackLibrary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-10-15.
 */
public class MapCallback<T> extends Callback<T>{

    protected Map<String, Object> mapArguments;

    public MapCallback(Method method, T context){
        this.method = method;
        this.context = context;
        this.mapArguments = new HashMap<String, Object>();
    }

    public MapCallback(Method method, T context, Map<String, Object> arguments){
        this.method = method;
        this.context = context;
        this.mapArguments = arguments;
    }

    public Map<String, Object> getMapArguments(){
        return this.mapArguments;
    }

    @Override
    public void invoke() throws InvocationTargetException, IllegalAccessException {
        this.method.invoke(this.context, this.mapArguments.values().toArray());
    }
}
