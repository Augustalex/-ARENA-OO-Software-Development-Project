package arena.tournament.leaderboard;

import arena.tournament.ITournament;
import arena.users.IPlayer;

import java.util.PriorityQueue;

/**
 * Created by Simon on 12/12/2016.
 */
public interface ILeaderboard {
    /**
     * Methof for initilazing the leaderboard. Should be used after all players
     * have entered the tournament.
     * @return
     */
    ILeaderboard initLeaderboard();

    /**
     * Method for updateing the leaderboard after each game.
     * @return
     */
    ILeaderboard updateLeaderboard();

    /**
     * Method for getting the leaderboard of the tournament.
     * @return leaderboard
     */
    ILeaderboard getLeaderboard();
}
