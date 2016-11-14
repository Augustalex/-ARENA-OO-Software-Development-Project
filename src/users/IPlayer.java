package users;

import league.League;
import tournament.Tournament;

/**
 * Represents the User type IPlayer.
 */
public interface IPlayer {

    League[] getLeagues();

    void addLeague(int playerID);

    void addTournament(Tournament tour);

    Tournament getAvailibleTournaments(League[] leagues);

    //getAvailibleTournaments(Leagues)
    //addTournament(tour1)
}
