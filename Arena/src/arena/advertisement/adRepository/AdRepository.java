package arena.advertisement.adRepository;

import arena.advertisement.ad.IPreferredAd;
import arena.advertisement.ad.PreferredAdFactory;
import arena.advertisement.adPreference.AdPreferenceFactory;
import arena.advertisement.adPreference.IAdPreference;
import arena.advertisement.adSpot.AdSpot;
import arena.metaInformation.advertisementMetaInformation.AdvertisementMetaInformation;
import arena.users.advertiser.IAdvertiser;

import java.util.List;
import java.util.function.Supplier;

/**
 * Returns AdSpots given a certain IAdPreference.
 * The returned AdSpot contains all Ads that match
 * the given IAdPreference.
 */
public interface AdRepository {

    class AdRepositoryHolder{
        static final AdRepository instance = ((Supplier<AdRepository>)(() -> {
            AdRepository repo = new QueueAdRepository();
            repo
                .addPreferredAd(PreferredAdFactory.newPreferredAd("/adImages/koolaid.gif",
                        AdPreferenceFactory.newPlayViewPreference(), new AdvertisementMetaInformation("koolaid", "The cooling ad", 2, 2.25)))
                .addPreferredAd(PreferredAdFactory.newPreferredAd("/adImages/nintendo.gif",
                        AdPreferenceFactory.newPlayViewPreference(), new AdvertisementMetaInformation("nintendo", "let the game begin",-1,3.75)))
                .addPreferredAd(PreferredAdFactory.newPreferredAd("/adImages/gameboy.gif",
                        AdPreferenceFactory.newPlayViewPreference(), new AdvertisementMetaInformation("gameboy", "let the portable game begin", -1, 6.75)))
                .addPreferredAd(PreferredAdFactory.newPreferredAd("/adImages/caprisun.gif",
                        AdPreferenceFactory.newPlayViewPreference(), new AdvertisementMetaInformation("caprisun", "Juicy!",2,8.75)))
                .addPreferredAd(PreferredAdFactory.newPreferredAd("/adImages/kawasaki.gif",
                        AdPreferenceFactory.newPlayViewPreference(), new AdvertisementMetaInformation("kawasaki", "Broken leg", 3, 10.25)))
            ;
            return repo;
        })).get();
    }

    static AdRepository get(){
        return AdRepositoryHolder.instance;
    };
    /**
     * Returns a new AdSpot containing all PreferredAds that match
     * the given IAdPreference.
     * @param preference
     * @return
     */
    AdSpot newAdSpot(IAdPreference preference);

    /**
     * Adds an Ad with a IAdPreference to the repository.
     * @param preferredAd
     * @return
     */
    AdRepository addPreferredAd(IPreferredAd preferredAd);

    List<IPreferredAd> getAdsFromOwner(IAdvertiser owner);

    void addAdPreference(IAdPreference preference);

    List<IAdPreference> getAdPreferences();

    AdRepository removeAd(IPreferredAd preferredAd);
}
