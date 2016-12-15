package arena.tournament;

import arena.games.game.IGame;
import arena.games.gameInformation.GameInformation;
import arena.metaInformation.tournamentMetaInformation.ITournamentMetaInformation;
import arena.tournament.leaderboard.ILeaderboard;
import arena.tournament.match.IMatch;
import arena.users.IPlayer;

import java.util.List;
import java.util.PriorityQueue;

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

    /**
     * Method for adding a match to the tournament.
     * @return
     */
    ITournament addMatchToTournament();

    /**
     * Method for setting the game of the tournament
     * @param game
     * @return
     */
    ITournament setGame(IGame game);

    /**
     * Method for gettign the gameInformation of the game of the tournament.
     * @return
     */
    GameInformation getGameInformation();

    /**
     * Method for getting the leaderboard of the tournament.
     * @return leaderboard
     */
    ILeaderboard getLeaderboard();

}
