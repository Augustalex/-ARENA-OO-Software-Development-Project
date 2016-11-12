package advertisement.adRepository;

import advertisement.adPreference.AdPreference;
import advertisement.ad.PreferredAd;
import advertisement.adQueue.AdPreferenceQueue;
import advertisement.adQueue.AdQueueFactory;
import advertisement.adSpot.AdSpot;
import advertisement.adSpot.AdSpotFactory;

/**
 * Implements {@link AdRepository} with a AdPreferenceQueue.
 */
public class QueueAdRepository implements AdRepository {

    private final AdPreferenceQueue queue = new AdQueueFactory<PreferredAd>().newPreferenceQueue();

    @Override
    public AdSpot newAdSpot(AdPreference preference) {
        return AdSpotFactory
                .newAdSpot(this.queue.getPreferredAds(preference));
    }

    @Override
    public AdRepository addPreferredAd(PreferredAd preferredAd) {
        queue.addAd(preferredAd);

        return this;
    }
}
