package tournament;



import metaInformation.TournamentMetaInformation;
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

    private TournamentConfiguration configuration;

    public Tournament(TournamentConfiguration configuration){

    }

    @Override
    public TournamentMetaInformation getTournamentMetaInformation() {
        return this.configuration.getMetaInformation();
    }

}
