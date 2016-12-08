package arena.advertisement.adRepository;

import arena.advertisement.ad.Ad;
import arena.advertisement.ad.IPreferredAd;
import arena.advertisement.ad.PreferredAdFactory;
import arena.advertisement.adPreference.AdPreferenceFactory;
import arena.advertisement.adPreference.IAdPreference;
import arena.advertisement.adQueue.AdPreferenceQueue;
import arena.advertisement.adQueue.AdQueueFactory;
import arena.advertisement.adSpot.AdSpot;
import arena.advertisement.adSpot.AdSpotFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import arena.users.advertiser.IAdvertiser;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements {@link AdRepository} with a AdPreferenceQueue.
 */
public class QueueAdRepository implements AdRepository {

    private final AdPreferenceQueue queue = new AdQueueFactory<IPreferredAd>().newPreferenceQueue();

    @Override
    public AdSpot newAdSpot(IAdPreference preference) {
        return AdSpotFactory
                .newAdSpot(this.queue.getPreferredAds(preference));
    }

    @Override
    public AdRepository addPreferredAd(IPreferredAd preferredAd) {
        queue.addAd(preferredAd);

        return this;
    }

    @Override
    public List<IPreferredAd> getAdsFromOwner(IAdvertiser owner) {
        //TODO implement
        List<IPreferredAd> ads = new ArrayList<>();
        Ad[] advertiserAds = queue.getPreferredAds(AdPreferenceFactory.newPlayViewPreference());
        for(Ad ad: advertiserAds){
            if(((IPreferredAd)ad).getMetaInformation().getAdId() == owner.getId())
                ads.add(((IPreferredAd)ad));
        }
        return ads;
    }

    @Override
    public void addAdPreference(IAdPreference preference) {
        //TODO implement
        throw new NotImplementedException();
    }

    @Override
    public List<IAdPreference> getAdPreferences() {
        //TODO implement
        throw new NotImplementedException();
    }

    @Override
    public AdRepository removeAd(IPreferredAd preferredAd) {
        queue.removeAd(preferredAd);
        return this;
    }
}
