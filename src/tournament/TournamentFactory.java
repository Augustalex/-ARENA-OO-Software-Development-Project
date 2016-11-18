package tournament;

import tournament.tournamentConfiguration.ITournamentConfiguration;

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
        ITournament tournament = new Tournament(configuration);

    }
}
