package boardGameLibrary.boardGame.move;

import boardGameLibrary.players.Player;

/**
 * Created by August on 2016-10-21.
 */
public interface MoveScore {

    int getMoveScore(Player player, Move move);
}
