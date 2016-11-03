package boardGameLibrary.eventWrappers;

import java.awt.*;

/**
 * Created by August on 2016-10-15.
 */
public class CellChangeEvent extends PositionEvent{

    public CellChangeEvent(Point position) {
        super(position);
    }

    public CellChangeEvent(int x, int y) {
        super(x, y);
    }
}
