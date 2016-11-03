package boardGameLibrary.views.javaFxViews;

import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import utilities.router.paneRouter.PaneViewController;

/**
 * Created by August on 2016-10-19.
 */
public abstract class FXMLViewController extends PaneViewController implements Initializable{

    private FXMLPaneLoader fxmlPaneLoader;

    public FXMLViewController(Pane container, String resourcePath){
        this.setContainer(container);
        this.fxmlPaneLoader = new FXMLPaneLoader(container, resourcePath);
    }

    @Override
    public void loadView() {
        try {
            this.fxmlPaneLoader.loadFXMLWith(this);
        }
        catch(Exception e){
            e.printStackTrace();
            //System.out.println(e.getMessage());
            //throw new ViewNotLoadedException();
        }
    }
}
