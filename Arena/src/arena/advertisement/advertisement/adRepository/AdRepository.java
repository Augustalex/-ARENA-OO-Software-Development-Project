package arena.advertisement.advertisement.adRepository;

import arena.advertisement.advertisement.ad.IPreferredAd;
import arena.advertisement.advertisement.adPreference.IAdPreference;
import arena.advertisement.advertisement.adSpot.AdSpot;
import arena.users.advertiser.IAdvertiser;

import java.util.List;

/**
 * Returns AdSpots given a certain IAdPreference.
 * The returned AdSpot contains all Ads that match
 * the given IAdPreference.
 */
public interface AdRepository {

    /**
     * Returns a new AdSpot containing all PreferredAds that match
     * the given IAdPreference.
     * @param preference
     * @return
     */
    AdSpot newAdSpot(IAdPreference preference);

    /**
     * Adds an Ad with a IAdPreference to the repository.
     * @param preferredAd
     * @return
     */
    AdRepository addPreferredAd(IPreferredAd preferredAd);

    List<IPreferredAd> getAdsFromOwner(IAdvertiser owner);

    void addAdPreference(IAdPreference preference);

    List<IAdPreference> getAdPreferences();
}
