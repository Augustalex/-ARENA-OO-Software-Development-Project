package tournament;

/**
 * Created by Johan on 2016-11-11.
 */
public class TournamentFactory {
    public static ITournament newTournament(){
        return new Tournament();
    }
}
