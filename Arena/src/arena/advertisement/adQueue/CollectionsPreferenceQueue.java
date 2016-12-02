package arena.advertisement.adQueue;

import arena.advertisement.ad.Ad;
import arena.advertisement.ad.IPreferredAd;
import arena.advertisement.adPreference.IAdPreference;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Implements {@link AdPreferenceQueue} and uses a Collection
 * Queue to implement all methods.
 */
public class CollectionsPreferenceQueue implements AdPreferenceQueue {

    private final Queue<IPreferredAd> queue = new LinkedBlockingQueue<>();

    @Override
    public void addAd(IPreferredAd ad) {
        queue.add(ad);
    }

    @Override
    public void removeAd(IPreferredAd ad) {
        queue.remove(ad);
    }

    @Override
    public IPreferredAd dequeue() {
        return queue.poll();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public Ad[] getPreferredAds(IAdPreference preference) {
        return queue.parallelStream()
                .filter(ad -> ad.getAdPreference().isPreferable(preference))
                .toArray(Ad[]::new);
    }
}
