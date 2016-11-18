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

    List<ITournament> getTournaments();

    void addTournamentToLeague(ITournament tournament);

    void addPlayerToLeague(IPlayer player);

    List<IPlayer> getPlayersInLeague();

    void printTournamentIDs();

    void printTournamentNames();
}
