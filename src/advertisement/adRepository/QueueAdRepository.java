package advertisement.adRepository;

import advertisement.ad.Ad;
import advertisement.adPreference.AdPreference;
import advertisement.ad.PreferredAd;
import advertisement.adQueue.AdPreferenceQueue;
import advertisement.adQueue.AdQueueFactory;
import advertisement.adSpot.AdSpot;
import advertisement.adSpot.AdSpotFactory;
import advertisement.ad.PreferredAdFactory;

/**
 * Implements {@link AdRepository} with a AdPreferenceQueue.
 */
public class QueueAdRepository implements AdRepository {

    private AdPreferenceQueue queue = new AdQueueFactory<PreferredAd>().newPreferenceQueue();

    @Override
    public AdSpot newAdSpot(AdPreference preference) {
        return AdSpotFactory
                .newAdSpot(this.queue.getPreferredAds(preference));
    }

    @Override
    public AdRepository addAd(Ad ad, AdPreference preference) {
        queue.addAd(PreferredAdFactory.newPreferredAd(ad, preference));

        return this;
    }

    @Override
    public AdRepository addPreferredAd(PreferredAd preferredAd) {
        queue.addAd(preferredAd);

        return this;
    }
}
