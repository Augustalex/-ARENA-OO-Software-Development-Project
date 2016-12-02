package boardGameLibrary.boardGame.match;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import boardGameLibrary.players.Player;
import boardGamePlugins.othello.board.OthelloBoard;
import boardGamePlugins.othello.board.OthelloBoardMoveMaker;

import java.io.Serializable;

/**
 * Created by August on 2016-10-19.
 */
public interface GameMatch extends Serializable {

    static GameMatch createGameMatch(String gameType, Player[] players, boolean isOnline) {

        BoardMoveMaker moveMaker;
        switch (gameType.toLowerCase()) {
            case "othello":
                moveMaker = new OthelloBoardMoveMaker(new OthelloBoard());
                moveMaker.setStartPawns(players);
                break;
            default:
                throw new IllegalArgumentException();
        }

        if (isOnline)
            return new OnlineGameMatch();
        else
            return new LocalGameMatch(moveMaker, players);
    }

    static GameMatch setupNewMatch(MatchSetup setup){
        GameMatch match = GameMatch.createGameMatch(setup.getGameType(), setup.getPlayers(), setup.isOnline());

        if(setup.snapshotSet())
            match.getBoardMoveMaker().getGameBoard().restoreGameBoard(setup.getSnapshot());

        return match;
    }

    void run();

    void turn(Player player);

    BoardMoveMaker getBoardMoveMaker();

    MoveProperties getMoveProperties();

    Player[] getPlayers();

}
