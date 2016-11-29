package indexedService;

import rest.ReST;
import rest.ReSTContainer;

import java.io.IOException;

/**
 * Created by August on 2016-11-29.
 */
public abstract class IndexedService extends ReSTContainer implements IIndexedService {

    private final int capacity;

    public IndexedService(int port, int capacity) throws IOException {
        super(port);
        this.capacity = capacity;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

}
