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

    public static void addTournamentConfiguration(ITournamentConfiguration tournamentStyle){
        configuration.add(tournamentStyle);
    }

    public static List<ITournamentConfiguration> getTournamentConfigurations(){
        return configuration.stream().collect(Collectors.toList());
    }

    public static ITournamentConfiguration newTournamentConfiguration(){
        return new TournamentConfiguration();
    }
}
