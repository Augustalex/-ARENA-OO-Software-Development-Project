package advertisement.ad;

import advertisement.adPreference.IAdPreference;
import metaInformation.MetaInformation;
import users.advertiser.IAdvertiser;

/**
 * Combines an Ad with a preference of where that Ad
 * should be displayed from within the application.
 */
public interface IPreferredAd extends Ad {

    IAdPreference getAdPreference();

    MetaInformation getMetaInformation();

    IAdvertiser getOwner();

}
