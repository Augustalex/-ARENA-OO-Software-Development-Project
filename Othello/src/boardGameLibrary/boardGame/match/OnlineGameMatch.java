package boardGameLibrary.boardGame.match;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import boardGameLibrary.players.Player;

/**
 * Created by August on 2016-10-19.
 */
public class OnlineGameMatch implements GameMatch {

    @Override
    public void run() {

    }

    @Override
    public void turn(Player player) {

    }

    @Override
    public BoardMoveMaker getBoardMoveMaker() {
        return null;
    }

    @Override
    public MoveProperties getMoveProperties() {
        return null;
    }

    @Override
    public Player[] getPlayers() {
        return new Player[0];
    }
}
