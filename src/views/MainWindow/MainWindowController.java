package views.MainWindow;

import views.FXMLViewController;
import views.ViewDimensionBinder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.swing.text.View;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends FXMLViewController{

    private FXMLViewController currentContentController = null;
    @FXML
    private BorderPane mainWindow;
    @FXML
    private Pane contentView;

    @FXML
    private HBox tabView;

    @FXML
    private Button playButton;

    @FXML
    private Button watchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ViewDimensionBinder.bindWidthToPercentageOfContainer(contentView, 1, mainWindow);
        ViewDimensionBinder.bindHeightToPercentageOfContainer(contentView, 0.9, mainWindow);

        bindTabViewDimensions(tabView, mainWindow);
        bindButtonDimensions(tabView);

        playButton.setOnAction(e -> {
            try{
                closeCurrentContentController();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PlayView/PlayView.fxml"));
                Parent parent = loader.load();
                this.currentContentController = loader.getController();

                contentView.getChildren().setAll(parent);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        });

    }

    @Override
    public void closeView() {
        closeCurrentContentController();
    }

    private void bindTabViewDimensions(Pane tabView, Pane container){
        ViewDimensionBinder.bindWidthToPercentageOfContainer(tabView, 1, container);
        ViewDimensionBinder.bindHeightToPercentageOfContainer(tabView, 0.1, container);
    }

    private void bindButtonDimensions(Pane tabView){
        for(Button button : tabView.getChildren().stream().filter(node -> node instanceof Button).toArray(Button[]::new)){
            ViewDimensionBinder.bindHeightToPercentageOfContainer(button, 1, tabView);
            ViewDimensionBinder.bindWidthToPercentageOfContainer(button, 0.25, tabView);
        }
    }

    private void closeCurrentContentController(){
        if(this.currentContentController != null)
            this.currentContentController.closeView();
    }
}
