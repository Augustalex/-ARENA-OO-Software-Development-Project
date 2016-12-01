package arena.advertisement.advertisement.adQueue;

import arena.advertisement.advertisement.ad.Ad;
import arena.advertisement.advertisement.ad.IPreferredAd;
import arena.advertisement.advertisement.adPreference.IAdPreference;

/**
 * Extension of AdQueue with the option of comparing matchable
 * AdPreferences to pertain all preferred ads.
 */
public interface AdPreferenceQueue extends AdQueue<IPreferredAd>{

    /**
     * Returns all ads that is preferable in relation to
     * the given {@link IAdPreference}.
     * @param preference
     * @return
     */
    Ad[] getPreferredAds(IAdPreference preference);

}
