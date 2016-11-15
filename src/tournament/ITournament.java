package tournament;

import tournament.tournamentMap.ITournamentMap;
import users.IPlayer;
import utilities.TimeDate;

import java.util.ArrayList;

/**
 * Interface for a Tournament
 */
public interface ITournament {
    void setJoinedPlayer(IPlayer player);
    ArrayList<IPlayer> getJoinedPlayers();

    void setTournamentMap(ITournamentMap tournamentMap);
    ITournamentMap getTournamentMap();

    void setTimer(TimeDate time);
    TimeDate getTimer();

    void joinTournament();

    String getTournamentName();

    int getTournamentID();

    //void getTournamentInfo();

    void AddPlayer(IPlayer player);
}
