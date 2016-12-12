package arena.users;

import arena.league.ILeague;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 2016-12-09.
 */
public class LeagueOwner extends Player implements ILeagueOwner{

    private ILeague league = null;

    private LeagueOwner(IUser user){
        super(user);
    }

    public static ILeagueOwner create(String name){
        return LeagueOwner.create(User.createMockUser(name));
    }

    public static ILeagueOwner create(IUser user){
        return new LeagueOwner(user)
                .setOwnedLeague(ILeague.globalLeague);
    }

    @Override
    public ILeague getOwnedLeague() {
        return league;
    }

    @Override
    public ILeagueOwner setOwnedLeague(ILeague league) {
        this.league = league;
        return this;
    }
}
