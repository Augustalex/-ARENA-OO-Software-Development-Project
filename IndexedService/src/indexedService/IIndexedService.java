package indexedService;

/**
 * Created by August on 2016-11-29.
 */
public interface IIndexedService {

    int getCapacity();

    void shutdown();

    void restart();

}
