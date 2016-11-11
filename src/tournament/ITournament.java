package tournament;

import tournament.tournamentMap.ITournamentMap;
import users.Player;
import utilities.TimeDate;

import java.util.ArrayList;

/**
 * Created by Johan on 2016-11-11.
 */
public interface ITournament {
    void setJoinedPlayer(Player player);
    ArrayList<Player> getJoinedPlayers();

    void setTournamentMap(ITournamentMap tournamentMap);
    ITournamentMap getTournamentMap();

    void setTimer(TimeDate time);
    TimeDate getTimer();

    void joinTournament();

    void AddPlayer(Player player);
}
