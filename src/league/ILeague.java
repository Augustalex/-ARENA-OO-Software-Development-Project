package league;

import tournament.Tournament;

import java.util.ArrayList;

/**
 * Created by Simon on 14/11/2016.
 */
public interface ILeague {

    String getLeagueName();

    int getLeagueID();

    public ArrayList<Tournament> getTournamentsInLeague();

    void addTounamentToLeague(Tournament tournament);
}
