/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.view;

import javafx.scene.layout.Pane;
import othello.view.GameBoard.KeyInputHandler;
import othello.view.GameBoard.MouseInputHandler;

/**
 * This class represents a sqaure in a board on which the game is played. The
 * cell reacts on GUI input.
 * @author Johan
 */
public class Cell extends Pane {
    /**
     * A cell holds information about its place in the gamebaord. It also holds
     * two instances of InputEvent-handlers to be able to trigger events on GUI 
     * input. 
     */
    private int i;
    private int j;
    private MouseInputHandler mouseHandler;
    private KeyInputHandler keyHandler;

    /**
     * Constructor taking values for all attributes and initializes them.
     * @param keyHandler, handler for key input
     * @param mouseHandler, handler for mouseevent
     * @param i, row of cell in the board
     * @param j, column of cell in the board
     */
    public Cell(KeyInputHandler keyHandler, MouseInputHandler mouseHandler, int i, int j) {
        this.i = i;
        this.j = j;
        this.keyHandler = keyHandler;
        this.mouseHandler = mouseHandler;
        setStyle("-fx-border-color: black; -fx-background-color: green;");
        this.setPrefSize(2000, 2000);
    }
    
    /**
     * Method activates all inputevents for a cell. Used seperately when game
     * starts to make sure cells do not act on inpu when no game is active.
     */
    public void setInputEvents(){
        this.setOnMouseClicked(e->mouseHandler.handle(e));
        this.setOnMouseEntered(e->mouseHandler.handle(e));
        this.setOnKeyPressed(e->keyHandler.handle(e));
    }
    
    /**
     * @return row of cell
     */
    public int getI(){
        return i;
    }

    /**
     * @return column of cell
     */
    public int getJ(){
        return j;
    }
    
    /**
     * Method changes border of cell to highlight when it is marked, either
     * by mouse or keyboard.
     */
    public void highlight(){
        setStyle("-fx-border-color: yellow; -fx-background-color: green;");
    }

    /**
     * Changes cell back to standard look when cell is demarked.
     */
    public void deHighlight(){
        setStyle("-fx-border-color: black; -fx-background-color: green;");
    }
}
