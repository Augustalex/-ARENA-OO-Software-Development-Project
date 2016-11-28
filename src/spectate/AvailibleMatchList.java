package spectate;

import league.ILeague;
import tournament.ITournament;
import tournament.match.IMatch;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Simon on 24/11/2016.
 */
public class AvailibleMatchList implements Serializable{

    ArrayList<IMatch> availibleMatches = new ArrayList<>();

    public AvailibleMatchList(ILeague league){
        //league.getTournaments().getMatches()
    }

    private void getAllMatchesOfTournament(ITournament tournament){
        tournament.getMatches();
    }

    public ArrayList<IMatch> getAvailibleMatches(){
        return availibleMatches;
    }
    public void viewAvailibeMatches(){
        for(int i = 0; i < availibleMatches.size(); i++){
            //System.out.println(availibleMatches.get(i).getMatchName());
        }
    }
}
