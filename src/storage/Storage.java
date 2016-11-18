package storage;

import utilities.router.stateRestorer.StateLoader;
import utilities.router.stateRestorer.StateWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by August on 2016-11-18.
 */
public class Storage implements IStorage {
    private Map<Class, Object> map = new HashMap<>();

    public static final String fileURL = "notadatabase.yoda";

    @Override
    public void store(Class clazz, Object object) {
        this.map.put(clazz, object);
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
