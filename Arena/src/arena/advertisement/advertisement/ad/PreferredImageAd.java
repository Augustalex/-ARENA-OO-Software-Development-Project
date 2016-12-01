package arena.advertisement.advertisement.ad;

import arena.advertisement.advertisement.adPreference.IAdPreference;
import javafx.scene.image.Image;
import arena.metaInformation.MetaInformation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import arena.users.advertiser.IAdvertiser;

/**
 * An implementation of the IPreferredAd interface.
 * Uses JavaFX Images to implement the Ad interface.
 */
public class PreferredImageAd implements IPreferredAd {

    private final IAdPreference adPreference;

    private final Image image;

    public PreferredImageAd(Ad<Image> ad, IAdPreference adPreference){
        this.adPreference = adPreference;
        this.image = ad.getContent();
    }

    /**
     * Returns the preference related to the encapsulated Ad.
     * @return
     */
    @Override
    public IAdPreference getAdPreference() {
        return this.adPreference;
    }

    @Override
    public MetaInformation getMetaInformation() {
        throw new NotImplementedException();
    }

    @Override
    public IAdvertiser getOwner() {
        throw new NotImplementedException();
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
