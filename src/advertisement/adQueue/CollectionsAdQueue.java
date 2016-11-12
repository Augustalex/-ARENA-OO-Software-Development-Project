package advertisement.adQueue;

import advertisement.ad.Ad;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Implements AdQueue with a Java Collections Queue.
 * @param <T>
 */
public class CollectionsAdQueue<T extends Ad> implements AdQueue<T> {

    private final Queue<T> queue = new LinkedBlockingQueue<>();

    @Override
    public void addAd(T ad) {
        queue.add(ad);
    }

    @Override
    public void removeAd(T ad) {
        queue.remove(ad);
    }

    @Override
    public T dequeue() {
        return queue.poll();
    }

    @Override
    public void clear() {
        this.queue.clear();
    }
}
