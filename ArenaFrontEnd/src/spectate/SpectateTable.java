package spectate;

import arena.league.ILeague;
import arena.league.League;
import arena.session.Session;
import arena.tournament.ITournament;
import arena.tournament.match.IMatch;
import arena.users.IPlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon on 24/11/2016.
 */
public class SpectateTable implements Serializable{

    private static class SpectateTableHolder{
        public static final SpectateTable instance = new SpectateTable();
    }

    private Session session = Session.getSession();

    private List<IMatch> availibleMatches = new ArrayList<>();
    private List<ITournament> availibleTournaments = new ArrayList<>();
    private List<ILeague> availibleLeagues = new ArrayList<>();

    public static SpectateTable get(){
        return SpectateTableHolder.instance;
    }

    private SpectateTable(){}

    public List<IMatch> returnAllPossibleMatches(){
        availibleLeagues = getLeagues();
        for (int i = 0; i < availibleLeagues.size(); i++){
            ILeague tempLeague = availibleLeagues.get(i);
            availibleTournaments = getTournaments(tempLeague);
        }
        for(int j = 0; j < availibleTournaments.size(); j++){
            ITournament tempTour = availibleTournaments.get(j);
            availibleMatches = getAvailableMatches(tempTour);
        }

        return availibleMatches;
    }

    public List<ILeague> getLeagues(){
        return new ArrayList<ILeague>(){{add(ILeague.globalLeague);}};
    }

    public List<ITournament> getTournaments(ILeague league){
       return league.getTournaments();
    }

    public List<IMatch> getAvailableMatches(ITournament tournament){
        return tournament.getMatches();
    }
}
