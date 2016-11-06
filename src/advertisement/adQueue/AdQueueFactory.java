package advertisement.adQueue;

import advertisement.ad.Ad;

/**
 * Returns an AdQueue of given type extended from Ad.
 *
 * @param <T>
 */
public class AdQueueFactory<T extends Ad>{

    public AdQueue<T> newAdQueue(){
    return new CollectionsAdQueue<>();
}

    public AdPreferenceQueue newPreferenceQueue(){
    return new CollectionsPreferenceQueue();
}
}
