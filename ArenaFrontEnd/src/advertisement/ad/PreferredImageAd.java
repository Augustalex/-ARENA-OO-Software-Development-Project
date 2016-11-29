package advertisement.ad;

import advertisement.adPreference.IAdPreference;
import javafx.scene.image.Image;
import metaInformation.MetaInformation;
import org.omg.CORBA.NO_IMPLEMENT;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import users.advertiser.IAdvertiser;

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
