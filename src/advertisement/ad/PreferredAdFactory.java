package advertisement.ad;

import advertisement.adPreference.AdPreference;
import advertisement.adPreference.AdPreferenceFactory;
import javafx.scene.image.Image;

import java.lang.reflect.ParameterizedType;

/**
 * Creates PreferredAds from a given Ad and AdPreference.
 */
public class PreferredAdFactory {

    public static PreferredAd newPreferredAd(Ad ad, AdPreference adPreference){
        if(PreferredAdFactory.isImageAd(ad))
            return new PreferredImageAd((Ad<Image>)ad, adPreference);
        else
            throw new IllegalArgumentException();
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

    private static boolean isImageAd(Ad ad){
        ParameterizedType type = (ParameterizedType) ad.getClass().getGenericSuperclass();

        //Check if parameter is of type Image
        return type.getActualTypeArguments()[0] == Image.class;
    }
}
