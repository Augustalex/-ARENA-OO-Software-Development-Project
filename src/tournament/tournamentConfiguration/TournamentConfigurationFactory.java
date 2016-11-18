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
     * Returns a new tournament configuration that is void of settings.
     * @return
     */
    public static ITournamentConfiguration newTournamentConfiguration(){
        return new TournamentConfiguration();
    }
}
