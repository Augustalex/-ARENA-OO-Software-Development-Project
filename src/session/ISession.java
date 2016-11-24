package session;

import users.IPlayer;

/**
 * Session interface contains objects for a current local session.
 *
 * When a program exits the session is stored and when the program
 * starts the session should be restored.
 */
public interface ISession {

    /**
     * Method for getting the player object linked to the current session.
     * @return IPlayer player
     */
    IPlayer getPlayer();

    /**
     * Method for getting the applied tournaments for the player
     * linked to this session.
     * @return AppliedTournaments
     */
    AppliedTournaments getAppliedTournaments();

}
