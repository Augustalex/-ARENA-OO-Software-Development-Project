package advertisement.ad;

import advertisement.adPreference.AdPreference;
import advertisement.adPreference.AdPreferenceFactory;
import advertisement.adPreference.AdPreferenceSheet;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates PreferredAds from a given Ad and AdPreference.
 */
public class PreferredAdFactory {

    public static PreferredAd newPreferredAd(Ad ad, AdPreference adPreference){
        return new PreferredImageAd(ad, adPreference);
    }

    public static PreferredAd newPreferredAd(String imageURL, AdPreference adPreference){
        return new PreferredImageAd(new ImageAd(imageURL), adPreference);
    }

    /**
     * Returns a PreferredAd with preference to be displayed
     * in the PlayView.
     *
     * Mainly used for testing purposes.
     * @param imageURL
     * @return
     */
    public static PreferredAd newPlayerViewPreferredAd(String imageURL){
        return new PreferredImageAd(
                new ImageAd(imageURL),
                AdPreferenceFactory.newPlayViewPreference()
        );
    }

    /**
     * Returns a PreferredAd with preference to be displayed
     * in the MainWindow.
     *
     * Mainly used for testing purposes.
     * @param imageURL
     * @return
     */
    public static PreferredAd newMainWindowPreferredAd(String imageURL){
        return new PreferredImageAd(
                new ImageAd(imageURL),
                AdPreferenceFactory.newMainWindowPreference());

    }
}
