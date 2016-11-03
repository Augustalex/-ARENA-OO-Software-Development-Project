package boardGamePlugins.othello.board.exceptions;

import boardGameLibrary.boardGame.move.Move;

/**
 * Created by August on 2016-10-02.
 */
public class IllegalMoveException extends RuntimeException {

    private Move move;

    public IllegalMoveException(Move move, String msg){
        super(msg);

        this.move = move;
    }

    public IllegalMoveException(Move move){
        this(move, "IllegalMoveException.");
    }
}
