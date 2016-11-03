package boardGamePlugins.othello.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.players.ComputerPlayer;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by August on 2016-10-21.
 */
public class GreedyAI extends ComputerPlayer {

    public GreedyAI(String name, Color color) {
        super(name, color);
    }

    @Override
    protected Move makeChoice(BoardMoveMaker boardMoveMaker) {
        ArrayList<CalculatedMove> legalMoves = boardMoveMaker.getAvailableMoves(this);

        if(legalMoves.size() == 0)
            throw new RuntimeException("No available moves for Player: " + this.getName());

        CalculatedMove choice = legalMoves.get(0);
        for(CalculatedMove move : legalMoves)
            if(isMoreGreedyMove(choice, move, boardMoveMaker))
                choice = move;

        return choice;
    }

    protected boolean isMoreGreedyMove(CalculatedMove standing, CalculatedMove candidate, BoardMoveMaker boardMoveMaker){
        if(inCorner(candidate.getActions()[0], boardMoveMaker.getGameBoard().getBoundaries())) {
            if (inCorner(standing.getActions()[0], boardMoveMaker.getGameBoard().getBoundaries())) {
                if (candidate.getScore() > standing.getScore())
                    return true;
            }
            else
                return true;
        }
        else if(onBorder(candidate.getActions()[0], boardMoveMaker.getGameBoard().getBoundaries())) {
            if (onBorder(standing.getActions()[0], boardMoveMaker.getGameBoard().getBoundaries())) {
                if (candidate.getScore() > standing.getScore())
                    return true;
            }
            else
                return true;
        }
        else if(candidate.getScore() > standing.getScore())
            return true;

        return false;
    }
}
