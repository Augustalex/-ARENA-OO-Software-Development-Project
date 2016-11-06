package advertisement.adQueue;

import advertisement.ad.Ad;

/**
 * Created by August on 2016-11-04.
 */
public interface AdQueue<T extends Ad> {

    /**
     * Adds the ad last in line of the queue.
     * @param ad
     */
    void addAd(T ad);

    /**
     * Removes the given Ad from the queue, does
     * not return it.
     *
     * @param ad
     */
    void removeAd(T ad);

    /**
     * Pops the Ad first in line of the queue and
     * returns it.
     * @return
     */
    T dequeue();

    /**
     * Clears the queue of all Ad elements.
     */
    void clear();

}
