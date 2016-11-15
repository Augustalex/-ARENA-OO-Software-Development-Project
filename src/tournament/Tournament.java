package tournament;



import metaInformation.TournamentMetaInformation;
import tournament.tournamentMap.ITournamentMap;
import users.IPlayer;
import utilities.TimeDate;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Implements the Tournament interface.
 */
public class Tournament implements ITournament, Serializable {
        private final ArrayList<IPlayer> joinedPlayers = new ArrayList<>();
        private ITournamentMap tournamentMap;
        private TimeDate startTime;
    private String tournamentName;
    private int tourID;

    @Override
    public void setJoinedPlayer(IPlayer player) {
        this.joinedPlayers.add(player);
    }

    @Override
    public ArrayList<IPlayer> getJoinedPlayers() {
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
    public String getTournamentName(){
        return tournamentName;
    }

    @Override
    public void setTournamentName(String name){
        this.tournamentName = name;
    }

    @Override
    public int getTournamentID(){
        return tourID;
    }

    @Override
    public void AddPlayer(IPlayer player) {
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
