package views.tabView;

import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import utilities.router.routerState.RouterState;
import views.FXMLViewController;

import java.io.IOException;

/**
 * Created by August on 2016-12-09.
 */
public class RouterButtonController {

    private javafx.util.Callback<java.lang.Class<?>, java.lang.Object> controllerCallback = null;
    private String fxmlPath;
    private Pane contentView;
    private Button routerButton;

    private ObjectProperty<FXMLViewController> currentController = null;
    private FXMLViewController contentViewController = null;

    public RouterButtonController(Button button, String fxmlPath, Pane contentView){
        this.routerButton = button;
        this.fxmlPath = fxmlPath;
        this.contentView = contentView;

        this.setupEventHandling();
    }

    public RouterButtonController setRouterButton(Button button){
        this.routerButton.setOnMouseClicked(e -> {});
        this.routerButton = button;
        this.setupEventHandling();
        return this;
    }

    public RouterButtonController setControllerFactory(javafx.util.Callback<java.lang.Class<?>, java.lang.Object> controllerCallback){
        this.controllerCallback = controllerCallback;
        return this;
    }

    public RouterButtonController setRouterPath(String fxmlPath){
        this.fxmlPath = fxmlPath;
        return this;
    }

    public RouterButtonController setContentView(Pane contentView){
        this.contentView = contentView;
        return this;
    }

    public RouterButtonController setViewControllerProperty(ObjectProperty<FXMLViewController> controllerProperty){
        this.currentController = controllerProperty;
        return this;
    }

    private void setupEventHandling(){
        this.routerButton.setOnMouseClicked(e -> {
            try {
                this.contentView.getChildren().setAll(route());
            } catch (IOException e1) {
                System.out.println("Could not load view for button " + this.routerButton.getText());
                e1.printStackTrace();
            }
        });
    }

    private Pane route() throws IOException {
        closeCurrentController();

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/views/" + this.fxmlPath));

        if(this.controllerCallback != null)
            loader.setControllerFactory(this.controllerCallback);

        Pane pane;
        try {
            pane = loader.load();
        }
        catch (Exception ex){
            System.out.println("Error");
            System.out.println(fxmlPath);
            ex.printStackTrace();
            throw new IOException();
        }
        this.currentController.set(loader.getController());
        return pane;
    }

    private void closeCurrentController(){
        if(this.currentController.get() != null)
            this.currentController.get().closeView();
    }
}
