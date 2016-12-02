package usersService;

import java.util.*;

/**
 * Stores unique object that are identified by unique identifiers.
 * @param <T> Type of objects stored
 * @param <I> Identifier class (i.e. int)
 */
public interface UniqueStorage<I, T> {

   void add(I objectId, T object);

    T get(I id);

    List<T> getAll();

    T remove(I id);

    void update(I id, T newObject);

}
