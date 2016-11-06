package advertisement.ad;

import advertisement.adPreference.AdPreference;
import javafx.scene.image.Image;

/**
 * An implementation of the PreferredAd interface.
 * Uses JavaFX Images to implement the Ad interface.
 */
public class PreferredImageAd implements PreferredAd {

    private AdPreference adPreference;

    private Image image;

    public PreferredImageAd(Ad ad, AdPreference adPreference){
        this.adPreference = adPreference;
        this.image = ad.getImage();
    }

    /**
     * Returns the preference related to the encapsulated Ad.
     * @return
     */
    @Override
    public AdPreference getAdPreference() {
        return this.adPreference;
    }

    /**
     * Returns the Image that represents the encapsulated Ad.
     * @return
     */
    @Override
    public Image getImage() {
        return this.image;
    }
}
