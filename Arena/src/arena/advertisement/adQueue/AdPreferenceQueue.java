package arena.advertisement.adQueue;

import arena.advertisement.ad.Ad;
import arena.advertisement.adPreference.IAdPreference;
import arena.advertisement.ad.IPreferredAd;

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
