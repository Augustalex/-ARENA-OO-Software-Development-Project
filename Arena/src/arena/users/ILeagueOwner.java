package arena.users;

import arena.league.ILeague;

import java.util.List;

/**
 * Created by August on 2016-12-09.
 */
public interface ILeagueOwner extends IPlayer{

    List<ILeague> getOwnedLeagues();

    ILeagueOwner addLeague(ILeague league);
}
