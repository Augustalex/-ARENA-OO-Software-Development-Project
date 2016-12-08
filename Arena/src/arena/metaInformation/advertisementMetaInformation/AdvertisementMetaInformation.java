package arena.metaInformation.advertisementMetaInformation;

import arena.metaInformation.MetaInformation;

/**
 * Created by Johan on 2016-12-01.
 */
public class AdvertisementMetaInformation extends MetaInformation {
    private int adID;
    private double amount;
    public AdvertisementMetaInformation(String name, String desc, int id, double amount){
        super.setName(name);
        super.setDescription(desc);
        this.adID = id;
        this.amount = amount;
    }

    public int getAdId(){
        return adID;
    }

    public double getAmount(){
        return amount;
    }
}
