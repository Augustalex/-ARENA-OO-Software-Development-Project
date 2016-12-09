package views.tabView;

import javafx.scene.control.Button;

/**
 * Created by August on 2016-12-09.
 */
public class RouterButton extends Button {

    private RouterButtonController controller = null;

    public RouterButton(String text){
        super(text);
    }

    public RouterButton setRouterButtonController(RouterButtonController controller){
        this.controller = controller;
        return this;
    }

    public RouterButtonController getRouterButtonController(){
        return this.controller;
    }


}
