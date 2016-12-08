/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.util;

/**
 * Class representing black markers in game
 * @author Johan
 */
public class BlackMarker extends Marker {
    /**
     * constructor setting color of the black marker in the superclass
     * @param color 
     */
    public BlackMarker(String color){
        super.color = color;
    }
    
     /**
     * Method compares two markers and checks if they are of opposite colors
     * @param comparisonMarker, marker to compare with
     * @return true or false
     */
    @Override
    public boolean isOpposite(Marker comparisonMarker){
         return comparisonMarker.getColour().equals("white");
     }
}
