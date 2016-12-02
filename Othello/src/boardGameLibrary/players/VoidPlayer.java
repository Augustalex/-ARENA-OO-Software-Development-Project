package boardGameLibrary.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import javafx.scene.paint.Color;

/**
 * Created by August on 2016-10-02.
 */
public class VoidPlayer extends Player{

    public VoidPlayer() {
        super("Void", Color.TRANSPARENT);
    }

    @Override
    public void makeMove(BoardMoveMaker boardMoveMaker, MoveProperties moveProperties) {

    }
}
