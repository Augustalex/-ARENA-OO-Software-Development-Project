package serviceClient.views.serviceListElement;

import javafx.scene.input.MouseEvent;

/**
 * Created by August on 2016-12-14.
 */
public interface IClickable {

    <T extends MouseEvent> void action(T event);
}
