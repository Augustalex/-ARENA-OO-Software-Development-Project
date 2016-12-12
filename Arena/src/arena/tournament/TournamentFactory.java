package arena.tournament;

import arena.games.game.GameFactory;
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
        return new Tournament(TournamentConfigurationFactory.newConfigurationMock("test tournament "));
    }

    public static ITournament newTournamentMock(String name){
        return new Tournament(TournamentConfigurationFactory
                .newConfigurationMock(name))
                .setGame(GameFactory.newMockOthelloGame())
                .addMatchToTournament();
    }

}
