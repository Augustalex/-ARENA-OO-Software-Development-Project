package storage;

import java.util.HashMap;
import java.util.Map;

/**
 * Interface for Storage
 */
public interface IStorage {

    /**
     * Stores an Object by its Class type.
     *
     * Only one instance per class can be stored at a time.
     * @param clazz
     * @param object
     */
    void store(Class clazz, Object object);

    /**
     * Retrieves an Object stored in the storage.
     *
     * Objects are stored by their Class type and there
     * can only be one instance of each class stored at a time.
     * @param clazz
     * @return
     */
    Object get(Class clazz);

    /**
     * Dumps the stored data onto disk. Can be restored.
     *
     * Utilizes Java Serialization.
     */
    void dump();

    /**
     * Restores a dumped storage file.
     *
     * Utilizes Java Serialization.
     */
    void restore();

}
