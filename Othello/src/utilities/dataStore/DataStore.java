package utilities.dataStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 2016-10-25.
 */
public abstract class DataStore<T> implements Store<T>{

    private ArrayList<T> store = new ArrayList<>();

    @Override
    public void store(T object) {
        this.store.add(object);
    }

    @Override
    public void remove(T object) {
        this.store.remove(object);
    }

    @Override
    public int size(){
        return this.store.size();
    }

    @Override
    public List<T> getStoreCopy(){
        List<T> list = new ArrayList<>();
        list.addAll(this.store);

        return list;
    }

    @Override
    public String toString(){
        return this.store.toString();
    }
}
