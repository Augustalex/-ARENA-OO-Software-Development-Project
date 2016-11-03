package boardGamePlugins.othello.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.boardGame.move.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by August on 2016-10-21.
 */
public class NaturalAI extends GreedyAI {

    public NaturalAI(String name, Color color) {
        super(name, color);
    }

    @Override
    protected Move makeChoice(BoardMoveMaker boardMoveMaker) {
        Random rand = new Random();
        ArrayList<CalculatedMove> legalMoves = boardMoveMaker.getAvailableMoves(this);

        CalculatedMove choice = legalMoves.get(0);
        for(CalculatedMove move : legalMoves)
            if(rand.nextBoolean() && this.isMoreGreedyMove(choice, move, boardMoveMaker))
                choice = move;

        return choice;
    }
}
