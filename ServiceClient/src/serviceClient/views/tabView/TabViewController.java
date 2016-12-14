package serviceClient.views.tabView;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import serviceClient.views.DimensionBinder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-12-08.
 */
public class TabViewController implements Initializable{

    private final RouterButton[] buttons;
    private Class currentContentController = null;

    private ObjectProperty<Class> currentController = new SimpleObjectProperty<>(null);
    private final Pane contentView;

    @FXML
    private HBox tabView;

    public TabViewController(Pane contentView, RouterButton[] buttons){
        this.contentView = contentView;
        this.buttons = buttons;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabView.getChildren().setAll(buttons);
        bindButtonDimensions(tabView);

    }

    /**
     *  Bind dimensions of the buttons to its containing tabView.
     *  Dimension factors are defines in variables in the method.
     * @param tabView
     */
    private void bindButtonDimensions(Pane tabView){
        double widthFactor = (double)1/buttons.length;
        double heightFactor = 1;

        for(Button button : tabView.getChildren().stream().filter(node -> node instanceof Button).toArray(Button[]::new)){
            DimensionBinder.bindHeightToPercentageOfContainer(button, heightFactor, tabView);
            DimensionBinder.bindWidthToPercentageOfContainer(button, widthFactor, tabView);
        }
    }

    /**
     * Closes the current "Content" controller.
     * The "Content" controller is the controller attached to the view
     * loaded into the pane responsible for holding the main content.
     */
    private void closeCurrentContentController(){
       /* if(this.currentContentController != null)
            this.currentContentController.closeView();*/
    }

}
