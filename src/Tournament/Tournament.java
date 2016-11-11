package Tournament;



import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Johan on 2016-11-11.
 */
public class Tournament implements ITournament, Serializable {
        private ArrayList <Player> joinedPlayers;
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
