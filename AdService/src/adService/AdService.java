package adService;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by August on 2016-12-01.
 */
public class AdService{

    private final AdPreferenceQueue queue = new AdQueueFactory<IPreferredAd>().newPreferenceQueue();

    public AdSpot newAdSpot(IAdPreference preference) {
        return AdSpotFactory
                .newAdSpot(this.queue.getPreferredAds(preference));
    }

    public AdRepository addPreferredAd(IPreferredAd preferredAd) {
        queue.addAd(preferredAd);

        return this;
    }

    public List<IPreferredAd> getAdsFromOwner(IAdvertiser owner) {
        //TODO implement
        throw new NotImplementedException();
    }

    public void addAdPreference(IAdPreference preference) {
        //TODO implement
        throw new NotImplementedException();
    }

    public List<IAdPreference> getAdPreferences() {
        //TODO implement
        throw new NotImplementedException();
    }

}
