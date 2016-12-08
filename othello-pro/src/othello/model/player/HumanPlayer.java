/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.model.player;

import othello.util.Marker;
import othello.model.gamestate.GameGrid;



/**
 * Human player class.
 * Human player may choose colour.
 * @author Simon
 */
public class HumanPlayer extends Player {
    /**
     * Attributes used to process a suggested move made in GUI
     */
    private int i;
    private int j;
    private boolean isChanged;
    
    /**
     * Constructor initialising variables, some by using in-arguments
     * @param name, String setting name in superclass
     * @param color, String setting color in superclass
     */
    public HumanPlayer(String name, String color){
        i = 0;
        j = 0;
        isChanged = false;
        setName(name);
        setColour(Marker.parseMarker(color));
    }
    
    /**
     * Overriding abstract method from superclass. The process of recieving a 
     * move suggested through GUI. Recieves which cell was chosen, checks if 
     * move is legal and responds accordingly. If legal move flag is set and
     * move can be performed. Otherwise is a string including a warning message
     * returned to calling structure.
     * @param grid, an instances of the current gamestate
     * @param i, row of the cell that was picked for a move
     * @param j, column of the cell that was picked for a move
     * @return String, empty if move is legal, so no warning message is shown,
     * otherwise a warning message is returned.
     */
    @Override
    public String processMove(GameGrid grid, int i, int j){
        if(grid.isLegalMove(this.getMarker(), i, j)){
            this.i = i;
            this.j = j;
            isChanged = true;
            return "";
        }
        return "Invalid move, try again.";
    }
    
    /**
     * Overriding abstract method from superclass. Waits for move ipnut through 
     * GUI. When flag is set, move is legal and this method calls requests the
     * move to be performed of the gamegrid. Method is executed in its own thread.
     * @param grid, the current gamestate
     */
    @Override
    public void nextMove(GameGrid grid){
        //create a loop/task which sleeps for tenth of a second and then check if
        //isChanged is true.
        new Thread(()->{
            while(true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.print("GameThread interrupted");
                }
                if(isChanged){
                    grid.flipMarkers(this.getMarker(), i ,j);
                    isChanged = false;
                    break;
                }
            }
        }).start();
    }
}
