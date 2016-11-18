package tournament;

import tournament.tournamentConfiguration.ITournamentConfiguration;
import tournament.tournamentConfiguration.TournamentConfigurationFactory;

/**
 * Creates a new Tournament given different sets of arguments.
 */
public class TournamentFactory {

    /**
     * Creates a new Tournament given no arguments. The tournament configuration and other
     * attributes is thus not set.
     * @return
     */
    public static ITournament newTournament(ITournamentConfiguration configuration){
        return new Tournament(configuration);
    }

    public static ITournament newTournamentMock(){
        return new Tournament(TournamentConfigurationFactory.newConfigurationMock());
    }

    public static ITournament newTournamentMock(String name){
        return new Tournament(TournamentConfigurationFactory.newConfigurationMock(name));
    }
}
