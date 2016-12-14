package serviceClient.views.tabView;

import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

/**
 * Follow the builder pattern to build a new RouterButton and its
 * RouterButtonController which requires a lot of different parameters,
 * too many for any constructor.
 */
public class RouterButtonBuilder {

    private RouterButton button = new RouterButton("");
    private RouterButtonController controller = new RouterButtonController(button, "", null);

    public RouterButtonBuilder buttonText(String text){
        button.setText(text);
        return this;
    }

    public RouterButtonBuilder fxmlPath(String fxmlPath){
        controller.setRouterPath(fxmlPath);
        return this;
    }

    public RouterButtonBuilder contentView(Pane contentView){
        controller.setContentView(contentView);
        return this;
    }

    public RouterButtonBuilder controllerFactory(javafx.util.Callback<Class<?>, Object> controllerFactory){
        controller.setControllerFactory(controllerFactory);
        return this;
    }

    public RouterButtonBuilder currentControllerProperty(ObjectProperty<Class> currentController){
        this.controller.setViewControllerProperty(currentController);
        return this;
    }

    public RouterButton build(){
        return this.button.setRouterButtonController(this.controller);
    }
}
