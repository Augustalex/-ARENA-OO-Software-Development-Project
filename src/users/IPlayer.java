package users;

import league.ILeague;
import tournament.ITournament;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the IUser type IPlayer.
 */
public interface IPlayer extends IUser {

    ArrayList<ILeague> getLeagues();

    //void addLeague(int playerID);

    //void addTournament(ITournament tour);

    List<ITournament> getAvailibleTournaments();

    //void bindTournamentToPlayer(ITournament tournament, IPlayer player);

    //getAvailibleTournaments(Leagues)
    //addTournament(tour1)
}
