package storage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-11-18.
 */
public interface IStorage {

    void store(Class clazz, Object object);

    Object get(Class clazz);

    void dump();

    void restore();
}
