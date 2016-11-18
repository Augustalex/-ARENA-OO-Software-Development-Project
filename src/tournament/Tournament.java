package tournament;



import metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import tournament.tournamentConfiguration.ITournamentConfiguration;

import java.io.Serializable;

/**
 * Implements the Tournament interface.
 */
public class Tournament implements ITournament, Serializable {

    private ITournamentConfiguration configuration;

    public Tournament(ITournamentConfiguration configuration){
        this.configuration = configuration;
    }

    @Override
    public ITournamentMetaInformation getTournamentMetaInformation() {
        return (ITournamentMetaInformation) this.configuration.getMetaInformation();
    }

}
