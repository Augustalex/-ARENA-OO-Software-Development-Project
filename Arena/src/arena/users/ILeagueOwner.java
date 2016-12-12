package arena.users;

import arena.league.ILeague;

/**
 * Created by August on 2016-12-09.
 */
public interface ILeagueOwner extends IPlayer{

    ILeague getOwnedLeague();

    ILeagueOwner setOwnedLeague(ILeague league);
}
