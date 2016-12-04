package views.handleAdvertisement.AdvertisementViewMocks;

/**
 * Created by Johan on 2016-12-03.
 */
public class AdvertisementMock {
    String name;
    String preference;
    double amount;
    int id;

    public AdvertisementMock(String name, String preference, double amount, int id){
        this.name = name;
        this.preference = preference;
        this.amount = amount;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPreference(){
        return this.preference;
    }

    public void setPreference(String preference){
        this.preference = preference;
    }

    public double getAmount(){
        return this.amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
}
