package boardGamePlugins.othello.board;

import boardGameLibrary.boardGame.board.GameBoard;
import boardGameLibrary.boardGame.match.BoardSnapshot;
import boardGameLibrary.players.Player;
import boardGameLibrary.players.VoidPlayer;
import boardGamePlugins.othello.pawn.OthelloPawn;

import java.awt.*;

/**
 * Created by August on 2016-10-15.
 */
public class OthelloBoard extends GameBoard<OthelloPawn> {

    private static final int width = 12;
    private static final int height = 12;

    public OthelloBoard() {
        super(OthelloBoard.width, OthelloBoard.height);

        for(int y = 0; y < OthelloBoard.height; y++)
            for(int x = 0; x < OthelloBoard.width; x++)
                this.setPawn(new Point(x, y), new OthelloPawn(new VoidPlayer()));

    }

    @Override
    public void restoreGameBoard(BoardSnapshot snapshot) {
        for (int y = 0; y < OthelloBoard.height; y++){
            for (int x = 0; x < OthelloBoard.width; x++) {
                Point position = new Point(x, y);
                this.setPawn(position, new OthelloPawn(snapshot.getOwnerAt(position)));
            }
        }
    }
}
