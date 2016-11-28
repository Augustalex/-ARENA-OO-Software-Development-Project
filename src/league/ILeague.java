package league;

import tournament.ITournament;
import tournament.Tournament;
import users.IPlayer;
import users.Player;

import java.util.List;

/**
 * Interface for League
 */
public interface ILeague {

    String getLeagueName();

    int getLeagueID();

    /**
     * Method for getting a list of the tournaments in
     * the current league.
     * @return List<ITournament> tournaments
     */
    List<ITournament> getTournaments();

    /**
     * Method for adding a tournament to the current
     * league.
     * @param tournament
     */
    void addTournamentToLeague(ITournament tournament);

    void addPlayerToLeague(IPlayer player);

    List<IPlayer> getPlayersInLeague();

    void printTournamentIDs();

    /**
     * Method for pritning out the names of the tournaments
     * in the league.
     */
    void printTournamentNames();
}
