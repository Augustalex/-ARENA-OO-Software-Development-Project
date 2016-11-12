package tournament.tournamentConfiguration;

/**
 * Creates a TournamentConfiguration given different sets of available arguments.
 */
public class TournamentConfigurationFactory {

    /**
     * Creates a new empty or "blank" Tournament Configuration.
     * @return
     */
    public static ITournamentConfiguration newTournamentConfiguration(){
        return new TournamentConfiguration();
    }
}
