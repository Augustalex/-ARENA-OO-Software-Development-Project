package users;

import league.ILeague;
import league.League;
import tournament.ITournament;
import tournament.Tournament;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the User type IPlayer.
 */
public interface IPlayer extends User{

    ArrayList<ILeague> getLeagues();

    //void addLeague(int playerID);

    //void addTournament(ITournament tour);

    List<ITournament> getAvailibleTournaments();

    //void bindTournamentToPlayer(ITournament tournament, IPlayer player);

    //getAvailibleTournaments(Leagues)
    //addTournament(tour1)
}
