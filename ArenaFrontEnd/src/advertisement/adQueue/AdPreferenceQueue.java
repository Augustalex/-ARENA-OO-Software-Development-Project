package advertisement.adQueue;

import advertisement.ad.Ad;
import advertisement.ad.IPreferredAd;
import advertisement.adPreference.IAdPreference;

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
