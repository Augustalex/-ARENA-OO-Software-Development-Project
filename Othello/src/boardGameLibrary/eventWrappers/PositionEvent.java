package boardGameLibrary.eventWrappers;

import java.awt.*;

/**
 * Created by August on 2016-10-15.
 */
public class PositionEvent {
    int x;
    int y;

    public PositionEvent(Point position){
        this.x = position.x;
        this.y = position.y;
    }

    public PositionEvent(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point getPosition(){
        return new Point(x, y);
    }
}
