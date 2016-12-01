package spectate;

import league.ILeague;
import session.Session;
import tournament.ITournament;
import tournament.match.IMatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Simon on 24/11/2016.
 */
public class SpectateTable implements Serializable{
    private Session session = Session.getSession();

    ArrayList<IMatch> availibleMatches = new ArrayList<>();
    List<ITournament> availibleTournaments = new ArrayList<>();
    List<ILeague> availibleLeagues = new ArrayList<>();

    public SpectateTable(){

    }

    public List<ILeague> getLeagues(){
        availibleLeagues = session.getPlayer().getLeagues();
        return availibleLeagues;
    }

    public List<ITournament> getTournaments(ILeague league){
        availibleTournaments = league.getTournaments();
        return availibleTournaments;
    }

    public ArrayList<IMatch> getAvailibleMatches(List<ITournament> availibleTournaments){
        for(int i = 0; i < availibleTournaments.size(); i++){
            ITournament tempTour = availibleTournaments.get(i);
            for(int j = 0; j < tempTour.getMatches().size(); j++){
                IMatch tempMatch = tempTour.getMatches().get(j);
                availibleMatches.add(tempMatch);
            }
        }
        return availibleMatches;
    }
}
