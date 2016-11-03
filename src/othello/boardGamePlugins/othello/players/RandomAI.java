package boardGamePlugins.othello.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.move.CalculatedMove;
import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.players.ComputerPlayer;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by August on 2016-10-21.
 */
public class RandomAI extends ComputerPlayer {

    public RandomAI(String name, Color color) {
        super(name, color);
    }

    @Override
    protected Move makeChoice(BoardMoveMaker boardMoveMaker) {

        ArrayList<CalculatedMove> legalMoves = boardMoveMaker.getAvailableMoves(this);

        if(legalMoves.size() <= 0)
            throw new RuntimeException("No available moves for Player: " + this.getName());
        else {
            Random rand = new Random();
            int choice;

            if(legalMoves.size() > 0) {
                choice = rand.nextInt(legalMoves.size() - 1);
                return legalMoves.get(choice);
            }
            throw new RuntimeException("No available moves for player.");
        }
    }
}
