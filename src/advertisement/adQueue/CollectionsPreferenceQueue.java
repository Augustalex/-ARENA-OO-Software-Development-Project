package advertisement.adQueue;

import advertisement.ad.Ad;
import advertisement.adPreference.AdPreference;
import advertisement.ad.PreferredAd;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Implements {@link AdPreferenceQueue} and uses a Collection
 * Queue to implement all methods.
 */
public class CollectionsPreferenceQueue implements AdPreferenceQueue {

    private Queue<PreferredAd> queue = new LinkedBlockingQueue<>();

    @Override
    public void addAd(PreferredAd ad) {
        queue.add(ad);
    }

    @Override
    public void removeAd(PreferredAd ad) {
        queue.remove(ad);
    }

    @Override
    public PreferredAd dequeue() {
        return queue.poll();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public Ad[] getPreferredAds(AdPreference preference) {
        return queue.parallelStream()
                .filter(ad -> ad.getAdPreference().isPreferable(preference))
                .toArray(Ad[]::new);
    }
}
