package boardGameLibrary.boardGame.board;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents all common 8 directions from a square
 * within a two-dimensional plane.
 *
 * Can be used for enumerating navigational algorithms withing a
 * two-dimensional field.
 */
public enum Direction {
    NORTH (270), NORTHEAST(315), EAST(0), SOUTHEAST(45),
    SOUTH(90), SOUTHWEST(135), WEST(180), NORTHWEST(225);

    private final int angle;

    /**
     * A static initialization of the direction hash map used in returning opposite directions
     * from another calling Direction enum.
     */
    private static Map<Integer, Direction> directionsMap = new HashMap<>();

    /**
     * Takes an angle in degrees and returns an instantiated Direction
     * enum. In the Java programming language, zero degrees point EAST.
     * @param angle in degrees
     */
    Direction(int angle){
        this.angle = angle;
    }


    static{
        for(Direction direction : Direction.values())
            Direction.directionsMap.put(direction.getAngle(), direction);
    }

    /**
     * Returns angle in degrees of the calling Direction enum.
     * @return int
     */
    public int getAngle(){
        return angle;
    }

    /**
     * Returns angle in radians of the calling Direction enum.
     * @return double
     */
    public double getRadianAngle(){
        return Math.toRadians(this.getAngle());
    }

    /**
     * Returns the opposite direction of the calling Direction enum.
     * @return Direction
     */
    public Direction getOppositeDirection(){
        return Direction.directionsMap.get((this.getAngle() + 180)%360);
    }
}
