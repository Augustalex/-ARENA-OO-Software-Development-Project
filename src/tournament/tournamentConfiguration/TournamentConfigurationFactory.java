package tournament.tournamentConfiguration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creates a TournamentConfiguration given different sets of available arguments.
 */
public class TournamentConfigurationFactory implements Serializable {

    /**
     * Creates a new empty or "blank" Tournament Configuration.
     * @return
     */
    private static List<ITournamentConfiguration> configuration = new ArrayList<>();

    /**
     * Add a Tournament Configuration to the static array list of Tournament Configurations.
     * @param tournamentStyle
     */
    public static void addTournamentConfiguration(ITournamentConfiguration tournamentStyle){
        configuration.add(tournamentStyle);
    }

    /**
     * Retrieve list of tournament configurations.
     * @return
     */
    public static List<ITournamentConfiguration> getTournamentConfigurations(){
        return configuration.stream().collect(Collectors.toList());
    }

    /**
     * Returns a new tournament configuration that is void of settings.
     * @return
     */
    public static ITournamentConfiguration newTournamentConfiguration(){
        ITournamentConfiguration tournamentConfiguration = new TournamentConfiguration();

        TournamentConfigurationFactory.addTournamentConfiguration(tournamentConfiguration);

        return tournamentConfiguration;
    }
}
