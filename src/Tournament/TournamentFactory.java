package Tournament;

/**
 * Created by Johan on 2016-11-11.
 */
public class TournamentFactory {
    public static ITournament getTournament(){
        return new Tournament();
    }
}
