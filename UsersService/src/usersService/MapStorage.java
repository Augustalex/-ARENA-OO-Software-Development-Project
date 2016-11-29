package usersService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by August on 2016-11-26.
 */
public class MapStorage<I, T> implements UniqueStorage<I, T>{

    private Map<I, T> storage = new HashMap<>();

    @Override
    public void add(I objectId, T object) {
        storage.put(objectId, object);
    }

    @Override
    public T get(I id) {
        return storage.get(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<T>(storage.values());
    }

    @Override
    public T remove(I id) {
        return storage.remove(id);
    }

    @Override
    public void update(I id, T newObject) {
        storage.replace(id, newObject);
    }

}
