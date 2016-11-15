package tournament.tournamentStyle;

/**
 * Created by Johan on 2016-11-15.
 */
public class TournamentStyleFactory {
    public ITournamentStyle newTournamentStyle(){
        return new TournamentStyle();
    }
}
