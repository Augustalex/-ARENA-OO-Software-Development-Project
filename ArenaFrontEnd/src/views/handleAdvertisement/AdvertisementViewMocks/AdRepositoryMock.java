package views.handleAdvertisement.AdvertisementViewMocks;

import arena.advertisement.adRepository.AdRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 2016-12-03.
 */
public class AdRepositoryMock {
    List<AdvertisementMock> adMockQueue = new ArrayList<>();

    private AdRepositoryMock(){
        AdvertisementMock newMock = new AdvertisementMock("Nintendo", "Play View", 37.25, 1);
        addNewAd(newMock);
    }

    private static class AdRepositoryMockHolder{
        private static final AdRepositoryMock INSTANCE = new AdRepositoryMock();
    }

    public static AdRepositoryMock getAdRepositoryMock(){
        return AdRepositoryMockHolder.INSTANCE;
    }

    public void addNewAd(AdvertisementMock mock){
        adMockQueue.add(mock);
    }

    public List<AdvertisementMock> getAds(int id){
        List<AdvertisementMock> newAdQueue = new ArrayList<>();
        for(AdvertisementMock ad: adMockQueue){
            if(ad.getId()==id)
                newAdQueue.add(ad);
        }
        return newAdQueue;
    }

    public void removeAd(int index){
        adMockQueue.remove(index);
    }
}
