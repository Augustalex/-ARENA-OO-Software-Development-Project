package boardGameLibrary.views.javaFxViews;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by August on 2016-10-10.
 */
public class FXMLPaneLoader{

    private Pane container;
    private String resourceName;

    public FXMLPaneLoader(Pane container, String resourceName){
        this.container = container;
        this.resourceName = resourceName;
    }

    protected void loadFXMLWith(Initializable controller) throws IOException {
        //Loading FXML from resource file (FXML file) defined by the controller Class path.
        FXMLLoader loader = new FXMLLoader(controller.getClass().getResource(this.resourceName));

        //Setting FXML ViewController class.
        loader.setController(controller);

        //Loading FXML into container Pane. Call to load() is safest with explicit casting to Parent.
        this.container.getChildren().setAll((Parent)loader.load());
    }



}
