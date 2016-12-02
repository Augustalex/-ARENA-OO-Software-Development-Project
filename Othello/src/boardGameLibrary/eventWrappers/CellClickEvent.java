package boardGameLibrary.eventWrappers;

import java.awt.*;

/**
 * Created by August on 2016-10-15.
 */
public class CellClickEvent {

    private int x;
    private int y;

    public CellClickEvent(int x, int y){
        this.x = x;
        this.y = y;
    }

    public CellClickEvent(Point position){
        this.x = position.x;
        this.y = position.y;
    }

    public Point getPosition(){
        return new Point(this.x, this.y);
    }
}
