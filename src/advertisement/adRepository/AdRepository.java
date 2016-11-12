package advertisement.adRepository;

import advertisement.ad.Ad;
import advertisement.adPreference.AdPreference;
import advertisement.ad.PreferredAd;
import advertisement.adSpot.AdSpot;

/**
 * Returns AdSpots given a certain AdPreference.
 * The returned AdSpot contains all Ads that match
 * the given AdPreference.
 */
public interface AdRepository {

    /**
     * Returns a new AdSpot containing all PreferredAds that match
     * the given AdPreference.
     * @param preference
     * @return
     */
    AdSpot newAdSpot(AdPreference preference);

    /**
     * Adds an Ad with a AdPreference to the repository.
     * @param preferredAd
     * @return
     */
    AdRepository addPreferredAd(PreferredAd preferredAd);
}
