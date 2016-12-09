package arena.users;

import arena.league.ILeague;
import arena.league.League;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 2016-12-09.
 */
public class LeagueOwner extends Player implements ILeagueOwner{

    private List<ILeague> leagues = new ArrayList<>();

    private LeagueOwner(IUser user){
        super(user);
    }

    public static ILeagueOwner create(String name){
        return LeagueOwner.create(User.createMockUser(name));
    }

    public static ILeagueOwner create(IUser user){
        return new LeagueOwner(user)
                .addLeague(ILeague.createMockLeague("Mock League AF"));
    }

    @Override
    public List<ILeague> getOwnedLeagues() {
        return leagues;
    }

    @Override
    public ILeagueOwner addLeague(ILeague league) {
        leagues.add(league);
        return this;
    }
}
