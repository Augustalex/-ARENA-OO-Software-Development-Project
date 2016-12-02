package boardGameLibrary.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import boardGameLibrary.boardGame.move.Move;
import boardGameLibrary.boardGame.move.PlayerAction;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;

/**
 * A player controlled by an algorithm. Referred to as an "AI" player.
 */
public abstract class ComputerPlayer extends Player{

    public ComputerPlayer(String name, Color color) {
        super(name, color);
    }

    /**
     * Won't use the CellClickProperty like other human players. Instead, it will launch a new {@link Thread}
     * where it will calculate a possible move to make upon the received {@link BoardMoveMaker}.
     *
     * The algorithm that calculates a Move is selected as the available move which flips the most {@link boardGameLibrary.boardGame.pawn.Pawn}s.
     * @param boardMoveMaker Used to apply a calculated {@link Move}
     * @param moveProperties
     */
    @Override
    public void makeMove(BoardMoveMaker boardMoveMaker, MoveProperties moveProperties) {

        new Thread(() -> {
            try {
                Platform.runLater(() -> moveProperties.legalMovesProperty().set(new ArrayList<>()));

                if(boardMoveMaker.getAvailableMoves(this).size() == 0) {
                    boardMoveMaker.makeMove(this, new Move(new PlayerAction[]{(new PlayerAction(0, 0))}));
                    return;
                }

                final Move choice = this.makeChoice(boardMoveMaker);

                Thread.sleep(300);

                Platform.runLater(() -> boardMoveMaker.makeMove(this, choice));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    protected abstract Move makeChoice(BoardMoveMaker boardMoveMaker);

    protected boolean onBorder(PlayerAction action, Dimension boundaries){
        if(action.getX() == 0 || action.getX() == boundaries.width-1)
            return true;
        else if(action.getY() == 0 || action.getY() == boundaries.height-1)
            return true;
        else
            return false;
    }

    protected boolean inCorner(PlayerAction action, Dimension boundaries){
        if(action.getX() == 0) {
            if (action.getY() == 0)
                return true;
            else if (action.getY() == boundaries.height - 1)
                return true;
        }
        else if(action.getX() == boundaries.width-1) {
            if (action.getY() == 0)
                return true;
            else if (action.getY() == boundaries.height - 1)
                return true;
        }

        return false;
    }
}
