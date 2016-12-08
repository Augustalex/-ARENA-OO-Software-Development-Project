package arena.session;

import arena.users.IPlayer;
import arena.users.IUser;

/**
 * Session interface contains objects for a current local arena.session.
 *
 * When a program exits the arena.session is stored and when the program
 * starts the arena.session should be restored.
 */
public interface ISession {

    void setUser(IUser user);

    /**
     * Method for getting the player object linked to the current arena.session.
     * @return IPlayer player
     */
    IPlayer getPlayer();

    IUser getUser();

    /**
     * Method for getting the applied tournaments for the player
     * linked to this arena.session.
     * @return AppliedTournaments
     */
    AppliedTournaments getAppliedTournaments();

}
