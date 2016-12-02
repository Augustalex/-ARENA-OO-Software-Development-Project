package arena.advertisement.ad;

import arena.advertisement.adPreference.IAdPreference;
import arena.metaInformation.MetaInformation;
import arena.users.advertiser.IAdvertiser;

/**
 * Combines an Ad with a preference of where that Ad
 * should be displayed from within the application.
 */
public interface IPreferredAd extends Ad {

    IAdPreference getAdPreference();

    MetaInformation getMetaInformation();

    IAdvertiser getOwner();

}
