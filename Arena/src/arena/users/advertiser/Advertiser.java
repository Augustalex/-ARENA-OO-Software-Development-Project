package arena.users.advertiser;

/**
 * Created by Johan on 2016-12-08.
 */
public class Advertiser implements IAdvertiser {
    int id;

    public Advertiser(int id){
        this.id=id;
    }

    @Override
    public int getId() {
        return id;
    }
}
