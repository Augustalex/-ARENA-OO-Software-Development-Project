/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.util;

import java.io.Serializable;

/**
 * Class used to represent a single cell in the gamegrid as a single object
 * @author Johan
 */
public class Point implements Serializable {
    /**
     * coordinates of cell
     */
    private int i;
    private int j;
    
    /**
     * constructor initializing attributes
     * @param i, row of cell
     * @param j, column of cell
     */
    public Point(int i, int j){
        this.i = i;
        this.j = j;
    }
    
    /**
     * @return row of the cell
     */
    public int getPointI(){
        return i;
    }
    
    /**
     * @return column of cell
     */
    public int getPointJ(){
        return j;
    }
}
