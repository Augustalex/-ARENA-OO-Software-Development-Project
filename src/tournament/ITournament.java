package tournament;

import metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import users.IPlayer;

/**
 * Interface for a Tournament
 */
public interface ITournament {

    ITournamentMetaInformation getTournamentMetaInformation();

    void applyPlayer(IPlayer player);

    AppliedPlayersList getAppliedPlayerList();

}
