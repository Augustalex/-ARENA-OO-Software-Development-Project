package arena.tournament;

import arena.metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import arena.tournament.match.IMatch;
import arena.users.IPlayer;

import java.util.List;

/**
 * Interface for a Tournament
 */
public interface ITournament {

    /**
     * Method for getting the metainformation about
     * the arena.tournament.
     * @return ITournamentMetaInformation metainformation
     */
    ITournamentMetaInformation getTournamentMetaInformation();

    /**
     * Method for applying a player to the arena.tournament.
     * @param player
     */
    void applyPlayer(IPlayer player);

    /**
     * Method for getting a list of players
     * applied to the arena.tournament.
     * @return AppliedPlayersList
     */
    AppliedPlayersList getAppliedPlayerList();

    /**
     * Method for getting a list of matches
     * in the arena.tournament
     * @return matchesList
     */
    List<IMatch> getMatches();

}
