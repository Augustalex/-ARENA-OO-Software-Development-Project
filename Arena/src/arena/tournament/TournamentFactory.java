package arena.tournament;

import arena.tournament.tournamentConfiguration.ITournamentConfiguration;
import arena.tournament.tournamentConfiguration.TournamentConfigurationFactory;

/**
 * Creates a new Tournament given different sets of arguments.
 */
public class TournamentFactory {

    /**
     * Creates a new Tournament given no arguments. The arena.tournament configuration and other
     * attributes is thus not set.
     * @return
     */
    public static ITournament newTournament(ITournamentConfiguration configuration){
        return new Tournament(configuration);
    }

    public static ITournament newTournamentMock(){
        return new Tournament(TournamentConfigurationFactory.newConfigurationMock("HB Tournament "));
    }

    public static ITournament newTournamentMock(String name){
        return new Tournament(TournamentConfigurationFactory.newConfigurationMock(name));
    }
}
