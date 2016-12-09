package views.MainWindow;

import javafx.fxml.Initializable;
import views.FXMLViewController;
import views.DimensionBinder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import views.tabView.TabViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends FXMLViewController implements Initializable{

    private FXMLViewController tabViewController = null;

    private Pane tabView = null;

    @FXML
    private BorderPane mainWindow;

    @FXML
    private Pane contentView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/tabView/TabView.fxml"));
            loader.setControllerFactory(c -> new TabViewController(contentView));
            this.tabView = loader.load();
            this.tabViewController = loader.getController();

            mainWindow.setTop(this.tabView);
        } catch (IOException e) {
            System.out.println("Could not load tab view.");
            e.printStackTrace();
        }

        setupContentView();
        setupTabView();
    }

    @Override
    public void closeView() {
        closeCurrentContentController();
    }

    /**
     *  Setting dimensions for the pane that will hold loaded content (such as Play View)
     *  Factors for dimensions are specified as variables in the method.
     */
    private void setupContentView(){
        double widthFactor = 1;
        double heightFactor = 0.9;

        DimensionBinder
                .bindDimensionsToFactorsOfContainer(
                        contentView,
                        DimensionBinder.dimensionsFactors(
                                widthFactor,
                                heightFactor
                        ),
                        mainWindow
                );
    }

    /**
     * Setups the dimension bindings of the TabView and its buttons.
     */
    private void setupTabView(){
        bindTabViewDimensions(tabView, mainWindow);
    }

    /**
     * Binds the tabView dimensions to the specified container.
     * The specific dimensions is specified in variables in the method.
     * @param tabView
     * @param container
     */
    private void bindTabViewDimensions(Pane tabView, Pane container){
        double tabViewWidthFactor = 1;
        double tabViewHeightFactor = 0.1;

        DimensionBinder
                .bindDimensionsToFactorsOfContainer(
                        tabView,
                        DimensionBinder.dimensionsFactors(
                                tabViewWidthFactor,
                                tabViewHeightFactor
                        ),
                        container
                );
    }

    /**
     * Closes the current "Content" controller.
     * The "Content" controller is the controller attached to the view
     * loaded into the pane responsible for holding the main content.
     */
    private void closeCurrentContentController(){
        this.tabViewController.closeView();
    }
}
