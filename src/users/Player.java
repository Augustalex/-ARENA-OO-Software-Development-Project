package users;

import league.League;
import tournament.Tournament;

/**
 * Created by Simon on 14/11/2016.
 */
public class Player implements IPlayer {

    League[] leagues;

    @Override
    public League[] getLeagues() {
        return leagues;
    }

    @Override
    public void addLeague(int playerID) {

    }

    @Override
    public void addTournament(Tournament tour) {

    }

    @Override
    public Tournament getAvailibleTournaments(League[] leagues) {
        return null;
    }
}
