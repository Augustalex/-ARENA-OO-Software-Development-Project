package league;

import tournament.ITournament;
import tournament.Tournament;
import users.IPlayer;
import users.Player;

import java.util.List;

/**
 * Created by Simon on 14/11/2016.
 */
public interface ILeague {

    String getLeagueName();

    int getLeagueID();

    List<ITournament> getTournamentsInLeague();

    void addTournamentToLeague(ITournament tournament);

    void addPlayerToLeague(IPlayer player);

    List<IPlayer> getPlayersInLeague();

    void printTournamentIDs();

    void printTournamentNames();
}
