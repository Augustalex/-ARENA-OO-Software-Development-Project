package users;

import league.League;
import tournament.ITournament;
import tournament.Tournament;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the User type IPlayer.
 */
public interface IPlayer {

    ArrayList<League> getLeagues();

    //void addLeague(int playerID);

    void addTournament(ITournament tour);

    List<ITournament> getAvailibleTournaments(ArrayList<League> leagues);

    //getAvailibleTournaments(Leagues)
    //addTournament(tour1)
}
