package boardGameLibrary.boardGame.move;

import java.awt.*;
import java.io.Serializable;

/**
 * A player action denotes a player interaction to a given element.
 * Most notably an element within a grid system which is represented
 * in this class by its position in X and Y coordinates.
 *
 * This class may also represent an action in form of a mouse click.
 */
public class PlayerAction implements Serializable {
    private int x;
    private int y;

    /**
     * Instantiates the class with X and Y coordinates from a Java AWT Point object.
     * The variables contained in the Point object are stored separately and not as another
     * Point object.
     * @param position
     */
    public PlayerAction(Point position){
        this.x = position.x;
        this.y = position.y;
    }

    public PlayerAction(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
