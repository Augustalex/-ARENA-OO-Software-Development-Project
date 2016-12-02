package utilities.dataStore;

import javafx.scene.paint.Color;

/**
 * Created by August on 2016-10-25.
 */
public interface Profile {

    String getName();

    void setName(String name);

    Color getColor();

    void setColor(Color color);

    Profile copy();

    String toString();

}
