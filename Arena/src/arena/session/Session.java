package arena.session;

import arena.session.exceptions.NotLoggedInException;
import arena.users.IUser;
import arena.users.UsersServiceProxy;
import hostProviderService.Host;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import rest.Delivery;
import usersService.IUsersService;

import javax.management.ServiceNotFoundException;
import java.io.Serializable;

/**
 * A arena.session contains a logged in IUser and information related to that
 * user. Additional information may be stored for the current arena.session
 * such as error logs etc.
 *
 * On log out or exit the Session may be stored on persistent storage
 * to later be reloaded into a new arena.session once the user logs in again.
 */
public class Session implements Serializable, ISession{

    private static final Session session = new Session();
    private ObjectProperty<IUser> userProperty = new SimpleObjectProperty<>(null);
    private AppliedTournaments appliedTournaments;

    private ServiceDirectoryProxy serviceDirectoryProxy = new ServiceDirectoryProxy(new Host(
            "10.10.107.76",
            2010
    ));

    private UsersServiceProxy usersServiceProxy;

    private Session(){
        this.appliedTournaments = new AppliedTournaments();

        serviceDirectoryProxy.getUsersService()
                .onCancel(() -> {
                    System.out.println("Could not get User Service Connection Details.");
                })
                .onDelivery(host -> {
                    System.out.println("Found service.");
                    usersServiceProxy = new UsersServiceProxy(host);
                });
    }

    public static Session getSession(){
        return Session.session;
    }

    @Override
    public void setUser(IUser user){
        this.userProperty.set(user);
    }

    @Override
    public IUser getUser(){
        if(userProperty.get() == null)
            throw new NotLoggedInException();
        else
            return userProperty.get();
    }

    @Override
    public AppliedTournaments getAppliedTournaments() {
        return appliedTournaments;
    }

    @Override
    public UsersServiceProxy getUsersService() throws ServiceNotFoundException {
        if(usersServiceProxy != null)
            return usersServiceProxy;
        else
            throw new ServiceNotFoundException();
    }

    public ObjectProperty<IUser> userProperty(){
        return this.userProperty;
    }
}
