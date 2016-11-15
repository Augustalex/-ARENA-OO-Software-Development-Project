package league;

import tournament.ITournament;
import tournament.Tournament;
import users.Player;

import java.util.List;

/**
 * Created by Simon on 14/11/2016.
 */
public interface ILeague {

    String getLeagueName();

    int getLeagueID();

    List<ITournament> getTournamentsInLeague();

    void addTournamentToLeague(Tournament tournament);

    void addPlayerToLeague(Player player);

    List<Player> getPlayersInLeague();
}
