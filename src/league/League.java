package league;

import tournament.Tournament;

import java.util.ArrayList;

/**
 * Created by Simon on 14/11/2016.
 */
public class League implements ILeague{
    String leagueName;
    int leagueID;
    ArrayList<Tournament> tournamentsInLeague = new ArrayList<>();

    public League(String name, int id){
        this.leagueName = name;
        this.leagueID = id;
    }
    public String getLeagueName() {
        return leagueName;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public ArrayList<Tournament> getTournamentsInLeague() {

        System.out.println("Name of tounaments: " + tournamentsInLeague);
        return tournamentsInLeague;
    }

    public void addTounamentToLeague(Tournament tournament){

        tournamentsInLeague.add(tournament);
    }
}
