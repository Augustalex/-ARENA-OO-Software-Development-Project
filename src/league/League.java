package league;

import tournament.ITournament;
import tournament.Tournament;
import users.IPlayer;
import users.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 14/11/2016.
 */
public class League implements ILeague{
    private String leagueName;
    private int leagueID;
    private ArrayList<IPlayer> playersInLeague = new ArrayList<>();
    private ArrayList<ITournament> tournamentsInLeague = new ArrayList<>();

    public League(String name, int id){
        this.leagueName = name;
        this.leagueID = id;
    }

    @Override
    public String getLeagueName() {
        return leagueName;
    }

    @Override
    public int getLeagueID() {
        return leagueID;
    }

    @Override
    public List<ITournament> getTournamentsInLeague() {
        printTournamentNames();
        return tournamentsInLeague;
    }

    @Override
    public void printTournamentNames(){
        for(int i = 0; i < tournamentsInLeague.size(); i++) {
            System.out.println("Name of tournaments: " + tournamentsInLeague.get(i).getTournamentMetaInformation().getTourName());
        }
    }

    @Override
    public void printTournamentIDs(){
        for(int i = 0; i < tournamentsInLeague.size(); i++) {
            System.out.println("ID's of tournaments: " + tournamentsInLeague.get(i).getTournamentID());
        }
    }

    @Override
    public void addTournamentToLeague(ITournament tournament){
        tournamentsInLeague.add(tournament);
    }

    @Override
    public void addPlayerToLeague(IPlayer player) {
        playersInLeague.add(player);
    }


    @Override
    public List<IPlayer> getPlayersInLeague() {
        return playersInLeague;
    }
}
