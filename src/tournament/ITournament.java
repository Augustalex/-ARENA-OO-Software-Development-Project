package tournament;

import metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import users.IPlayer;

/**
 * Interface for a Tournament
 */
public interface ITournament {

    /**
     * Method for getting the metainformation about
     * the tournament.
     * @return ITournamentMetaInformation metainformation
     */
    ITournamentMetaInformation getTournamentMetaInformation();

    /**
     * Method for applying a player to the tournament.
     * @param player
     */
    void applyPlayer(IPlayer player);

    /**
     * Method for getting a list of players
     * applied to the tournament.
     * @return AppliedPlayersList
     */
    AppliedPlayersList getAppliedPlayerList();

}
