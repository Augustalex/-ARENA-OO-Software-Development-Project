package advertisement.adQueue;

import advertisement.ad.Ad;
import advertisement.adPreference.AdPreference;
import advertisement.ad.PreferredAd;

/**
 * Extension of AdQueue with the option of comparing matchable
 * AdPreferences to pertain all preferred ads.
 */
public interface AdPreferenceQueue extends AdQueue<PreferredAd>{

    /**
     * Returns all ads that is preferable in relation to
     * the given {@link AdPreference}.
     * @param preference
     * @return
     */
    Ad[] getPreferredAds(AdPreference preference);

}
