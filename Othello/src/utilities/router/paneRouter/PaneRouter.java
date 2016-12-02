package utilities.router.paneRouter;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilities.router.Router;

import java.util.Map;

/**
 * Created by August on 2016-10-10.
 */
public class PaneRouter extends Router {

    /**
     * Container object into which views are loaded.
     */
    private Pane container;

    public PaneRouter(Pane container){
        this.container = container;
    }

    @Override
    public void routeToView(String viewId, Map data) {
        System.out.println("Route was called with: " + viewId + ", and a map: " + data.toString());

        PaneViewController viewController = PaneViewController.create(this.container, viewId, data);
        viewController.loadView();
    }

    public void setupSaveOnClose(Stage stage){
        stage.setOnHiding(e -> this.save());
    }

}
