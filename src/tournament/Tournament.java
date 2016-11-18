package tournament;



import metaInformation.TournamentMetaInformation;
import tournament.tournamentConfiguration.ITournamentConfiguration;
import tournament.tournamentConfiguration.TournamentConfiguration;
import tournament.tournamentMap.ITournamentMap;
import users.IPlayer;
import utilities.TimeDate;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Implements the Tournament interface.
 */
public class Tournament implements ITournament, Serializable {

    private ITournamentConfiguration configuration;

    public Tournament(ITournamentConfiguration configuration){
        this.configuration = configuration;
    }

    @Override
    public TournamentMetaInformation getTournamentMetaInformation() {
        return this.configuration.getMetaInformation();
    }

}
