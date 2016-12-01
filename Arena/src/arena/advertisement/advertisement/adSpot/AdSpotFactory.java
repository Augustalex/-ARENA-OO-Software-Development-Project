package arena.advertisement.advertisement.adSpot;

import arena.advertisement.advertisement.ad.Ad;

/**
 * Creates AdSpots from lists of Ads.
 */
public class AdSpotFactory {

    public static AdSpot newAdSpot(Ad[] ads){
        return new QueueAdSpot(ads);
    }

}
