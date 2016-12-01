package arena.session;

import arena.users.IPlayer;

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

    private IPlayer player;
    private AppliedTournaments appliedTournaments;

    private Session(){
        this.appliedTournaments = new AppliedTournaments();
    }

    public static Session getSession(){
        return Session.session;
    }

    public void setPlayer(IPlayer player){
        this.player = player;
    }

    @Override
    public IPlayer getPlayer(){
        return player;
    }

    @Override
    public AppliedTournaments getAppliedTournaments() {
        return appliedTournaments;
    }

}
