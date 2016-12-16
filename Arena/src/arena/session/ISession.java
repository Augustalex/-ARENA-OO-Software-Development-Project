package arena.session;

import arena.streamService.StreamServiceProxy;
import arena.users.IUser;
import arena.users.UsersServiceProxy;
import usersService.IUsersService;

import javax.management.ServiceNotFoundException;

/**
 * Session interface contains objects for a current local arena.session.
 *
 * When a program exits the arena.session is stored and when the program
 * starts the arena.session should be restored.
 */
public interface ISession {

    void setUser(IUser user);

    IUser getUser();

    /**
     * Method for getting the applied tournaments for the player
     * linked to this arena.session.
     * @return AppliedTournaments
     */
    AppliedTournaments getAppliedTournaments();

    UsersServiceProxy getUsersService() throws ServiceNotFoundException;

    StreamServiceProxy getStreamService();
}
