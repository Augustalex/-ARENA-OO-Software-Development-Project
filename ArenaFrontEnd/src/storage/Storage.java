package storage;

import utilities.router.stateRestorer.StateLoader;
import utilities.router.stateRestorer.StateWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements interface IStorage
 */
public class Storage implements IStorage {
    private Map<Class, Object> map = new HashMap<>();

    public static final String fileURL = "notadatabase.yoda";

    @Override
    public void store(Class clazz, Object object) {
        this.map.put(clazz, object);
    }

    @Override
    public Object get(Class clazz) {
        return map.get(clazz);
    }

    @Override
    public void dump() {
        StateWriter<Map<Class, Object>> stateWriter = new StateWriter<>(Storage.fileURL);
        stateWriter.writeState(this.map);
    }

    @Override
    public void restore() {
        StateLoader stateLoader = new StateLoader(Storage.fileURL);
        try {
            this.map = (Map<Class, Object>) stateLoader.restoreState();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
