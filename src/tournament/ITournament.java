package tournament;

import metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import metaInformation.tournamentMetaInformation.TournamentMetaInformation;

/**
 * Interface for a Tournament
 */
public interface ITournament {

    ITournamentMetaInformation getTournamentMetaInformation();

}
