package tournament;

import metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import tournament.match.IMatch;
import users.IPlayer;

import java.util.List;

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

    /**
     * Method for getting a list of matches
     * in the tournament
     * @return matchesList
     */
    List<IMatch> getMatches();

}
