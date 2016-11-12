package advertisement.ad;

import advertisement.adPreference.AdPreference;
import javafx.scene.image.Image;

/**
 * An implementation of the PreferredAd interface.
 * Uses JavaFX Images to implement the Ad interface.
 */
public class PreferredImageAd implements PreferredAd {

    private final AdPreference adPreference;

    private final Image image;

    public PreferredImageAd(Ad<Image> ad, AdPreference adPreference){
        this.adPreference = adPreference;
        this.image = ad.getContent();
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
    public Image getContent() {
        return this.image;
    }
}
