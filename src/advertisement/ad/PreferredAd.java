package advertisement.ad;

import advertisement.adPreference.AdPreference;

/**
 * Combines an Ad with a preference of where that Ad
 * should be displayed from within the application.
 */
public interface PreferredAd extends Ad {

    AdPreference getAdPreference();

}
