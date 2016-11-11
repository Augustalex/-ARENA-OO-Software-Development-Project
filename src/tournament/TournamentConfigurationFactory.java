package tournament;

/**
 * Created by Patric on 2016-11-11.
 */
public class TournamentConfigurationFactory {

    public static ITournamentConfiguration newTournamentConfiguration(){
        return new TournamentConfiguration();
    }
}
