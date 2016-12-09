package arena.session;

import arena.session.exceptions.NotLoggedInException;
import arena.users.IPlayer;
import arena.users.IUser;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

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

    private IPlayer player = null;

    private ObjectProperty<IUser> userProperty = new SimpleObjectProperty<>(null);

    private AppliedTournaments appliedTournaments;

    private Session(){
        this.appliedTournaments = new AppliedTournaments();
    }

    public static Session getSession(){
        return Session.session;
    }

    public void setPlayer(IPlayer player){
        this.player = player;
        System.out.println("New player for Session is " + player.getName());
    }

    @Override
    public void setUser(IUser user){
        this.userProperty.set(user);
    }

    @Override
    public IPlayer getPlayer(){
        if(player == null)
            throw new NotLoggedInException();
        else
            return player;
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

    public ObjectProperty<IUser> userProperty(){
        return this.userProperty;
    }
}
