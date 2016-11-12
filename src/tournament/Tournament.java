package tournament;



import tournament.tournamentMap.ITournamentMap;
import users.Player;
import utilities.TimeDate;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Implements the Tournament interface.
 */
public class Tournament implements ITournament, Serializable {
        private final ArrayList<Player> joinedPlayers = new ArrayList<>();
        private ITournamentMap tournamentMap;
        private TimeDate startTime;

    @Override
    public void setJoinedPlayer(Player player) {
        this.joinedPlayers.add(player);
    }

    @Override
    public ArrayList<Player> getJoinedPlayers() {
        return joinedPlayers;
    }

    @Override
    public ITournamentMap getTournamentMap() {
        return tournamentMap;
    }

    @Override
    public TimeDate getTimer() {
        return startTime;
    }

    @Override
    public void joinTournament() {

    }

    @Override
    public void AddPlayer(Player player) {
        /*if(this.startTime >= getCurrentTime()) */
        setJoinedPlayer(player);
        // Else do something
    }

    @Override
    public void setTimer(TimeDate time) {
        this.startTime = time;
    }

    @Override
    public void setTournamentMap(ITournamentMap tournamentMap) {
        this.tournamentMap = tournamentMap;
    }
}
