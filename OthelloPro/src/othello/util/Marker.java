/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.util;

/**
 * Abstract class representing markers in the game
 * @author Johan
 */
public abstract class Marker {
    /**
     * color of the marker
     */
    protected String color;
    
    /**
     * constructor
     */
    public Marker(){}
    
    /**
     * Parser, checking input color and decides which kind of marker to create
     * @param color, String type
     * @return a concrete marker: Black, White or Empty
     */
    public static Marker parseMarker(String color){
        switch(color){
            case "white":
                return new WhiteMarker(color);
            case "black":
                return new BlackMarker(color);
            default:
                return new EmptyMarker();    
        }
    }
    
    /**
     * @return String representing color of marker
     */
    public String getColour(){
        return color; 
    }
    
    /**
     * Method compares two markers and checks if the are of the same kind
     * @param comparisonColor, color of marker to compare with
     * @return true or false
     */
    public boolean markerEquals(String comparisonColor){
        return(color.equals(comparisonColor));
    }
    
    /**
     * Abstract method for comparing two markers. 
     * @param comparisonMarker, marker to compare with
     * @return true or false
     */
    public abstract boolean isOpposite(Marker comparisonMarker);
}
