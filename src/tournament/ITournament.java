package tournament;

import metaInformation.TournamentMetaInformation;
import users.IPlayer;

/**
 * Interface for a Tournament
 */
public interface ITournament {

    TournamentMetaInformation getTournamentMetaInformation();

    void applyPlayer(IPlayer player);

    AppliedPlayersList getAppliedPlayerList();

}
