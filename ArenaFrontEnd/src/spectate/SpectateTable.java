package spectate;

import arena.league.ILeague;
import arena.session.Session;
import arena.tournament.ITournament;
import arena.tournament.match.IMatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Simon on 24/11/2016.
 */
public class SpectateTable implements Serializable{

    private static class SpectateTableHolder{
        public static final SpectateTable instance = new SpectateTable();
    }

    private Session session = Session.getSession();

    List<IMatch> availibleMatches = new ArrayList<>();
    List<ITournament> availibleTournaments = new ArrayList<>();
    List<ILeague> availibleLeagues = new ArrayList<>();

    public static SpectateTable get(){
        return SpectateTableHolder.instance;
    }

    public List<IMatch> returnAllPossibleMatches(){
        availibleLeagues = getLeagues();
        for (int i = 0; i < availibleLeagues.size(); i++){
            ILeague tempLeague = availibleLeagues.get(i);
            availibleTournaments = getTournaments(tempLeague);
        }
        for(int j = 0; j < availibleTournaments.size(); j++){
            ITournament tempTour = availibleTournaments.get(j);
            availibleMatches = getAvailibleMatches(tempTour);
        }
        return availibleMatches;

    }

    public List<ILeague> getLeagues(){
        return session.getPlayer().getLeagues();
    }

    public List<ITournament> getTournaments(ILeague league){
       return league.getTournaments();
    }

    public List<IMatch> getAvailibleMatches(ITournament tournament){
         List<IMatch> matches = new ArrayList<>();
            for(int j = 0; j < tournament.getMatches().size(); j++){
                IMatch tempMatch = tournament.getMatches().get(j);
                availibleMatches.add(tempMatch);
            }
        return matches;
    }
}
