package arena.advertisement.ad;

import arena.advertisement.adPreference.IAdPreference;
import arena.metaInformation.MetaInformation;
import arena.metaInformation.advertisementMetaInformation.AdvertisementMetaInformation;
import arena.users.advertiser.IAdvertiser;
import javafx.scene.image.Image;
import org.omg.CORBA.NO_IMPLEMENT;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * An implementation of the IPreferredAd interface.
 * Uses JavaFX Images to implement the Ad interface.
 */
public class PreferredImageAd implements IPreferredAd {

    private final IAdPreference adPreference;
    private final Image image;
    private final AdvertisementMetaInformation metaInformation;

    public PreferredImageAd(Ad<Image> ad, IAdPreference adPreference, AdvertisementMetaInformation adMetaInformation){
        this.adPreference = adPreference;
        this.image = ad.getContent();
        this.metaInformation = adMetaInformation;
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
    public AdvertisementMetaInformation getMetaInformation() {
        return this.metaInformation;
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
