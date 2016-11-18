package storage;

import java.util.HashMap;
import java.util.Map;

/**
 * Interface for Storage
 */
public interface IStorage {

    void store(Class clazz, Object object);

    Object get(Class clazz);

    void dump();

    void restore();
    //TODO Se över method namnen, de beskriver inte riktigt va de gör
}
