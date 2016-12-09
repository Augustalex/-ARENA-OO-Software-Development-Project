package arena.users.advertiser;

import arena.users.IOperator;
import arena.users.IUser;
import arena.users.Operator;
import arena.users.User;

/**
 * Created by Johan on 2016-12-08.
 */
public class Advertiser implements IAdvertiser {

    private final IUser user;

    private Advertiser(IUser user){
        this.user = user;
    }

    public static IAdvertiser create(String name){
        return Advertiser.create(User.createMockUser(name));
    }

    public static IAdvertiser create(IUser user){
        return new Advertiser(user);
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public int getId() {
        return user.getId();
    }
}
