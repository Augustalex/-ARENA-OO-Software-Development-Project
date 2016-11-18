package session;

import users.IPlayer;

/**
 * Session interface contains objects for a current local session.
 *
 * When a program exits the session is stored and when the program
 * starts the session should be restored.
 */
public interface ISession {

    IPlayer getPlayer();

    AppliedTournaments getAppliedTournaments();

}
