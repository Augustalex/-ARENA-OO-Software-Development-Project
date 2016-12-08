/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.util;

/**
 * Class representing no marker, ie an empty cell in gamegrid
 * @author Johan
 */
public class EmptyMarker extends Marker{
    /**
     * constructor setting color in superclass to an empty string, representing
     * no marker.
     */
    public EmptyMarker(){
        super.color = "";
    }
    
     /**
     * Method compares two markers and checks if they are of opposite colors.
     * For an empty marker this method should always return false
     * @param comparisonMarker, marker to compare with
     * @return false
     */
    public boolean isOpposite(Marker comparisonMarker){
        return false;
    }
}
