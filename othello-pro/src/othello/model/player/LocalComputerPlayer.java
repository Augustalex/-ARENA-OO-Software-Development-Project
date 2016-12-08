/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.model.player;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import othello.util.Marker;
import othello.model.gamestate.GameGrid;
import othello.util.Point;

/**
 * Local computer player
 * @author Simon
 */
public class LocalComputerPlayer extends Player {
    
    /**
     * Constructor setting player attributes
     * @param name, String setting name in superclass
     * @param color, String setting color in superclass
     */
    public LocalComputerPlayer(String name, String color){
        setName(name);
        setColour(Marker.parseMarker(color));
    }
    
    /**
     * Method returns warning message to HumanPlayer, which is shown through GUI,
     * if player clicks the GUI when it is computer player's turn.
     * @param grid, current gamestate
     * @param i, row of clicked cell
     * @param j, column of clicked cell
     * @return String representing warning message
     */
    @Override
    public String processMove(GameGrid grid, int i, int j){
        return "It's not your turn";
    }

    /**
     * Method requests the gamegrid for a list of all valid moves. It then 
     * randomly picks a move from the list.
     * The method runs in a separate thread to not slow down the UI of the game.
     * @param grid, current gamestate
     */
    @Override 
    public void nextMove(GameGrid grid){
        new Thread(()->{
            try {
                Thread.sleep((int)(Math.random()*2000));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            ArrayList<Point> moveList = grid.calculatePlayerMoves(super.getMarker());
            Point selection = selectAMove(moveList);
            grid.flipMarkers(super.getMarker(), selection.getPointI(), selection.getPointJ());
        }).start();
    }
    
    /**
     * The process of randomly selecting a move from a list of valid moves
     * @param list, list of moves
     * @return Point, representing a move
     */
    private Point selectAMove(ArrayList<Point> list){
        int index = (int)(Math.random()*list.size());
        return list.get(index);
    }
}
