package advertisement.adSpot;

import advertisement.ad.Ad;

/**
 * Creates AdSpots from lists of Ads.
 */
public class AdSpotFactory {

    public static AdSpot newAdSpot(Ad[] ads){
        return new QueueAdSpot(ads);
    }

}
