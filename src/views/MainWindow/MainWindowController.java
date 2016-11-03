package views.MainWindow;

import views.ViewDimensionBinder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

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
        ViewDimensionBinder.bindOneToOneDimension(
                contentView.minWidthProperty(),
                contentView.maxWidthProperty(),
                mainWindow.widthProperty()
        );

        ViewDimensionBinder.bindOneToOneDimension(
                contentView.minHeightProperty(),
                contentView.maxHeightProperty(),
                mainWindow.heightProperty().multiply(0.9)
        );

        bindTabViewDimensions(tabView, mainWindow);
        bindButtonDimensions(tabView);


        playButton.setOnAction(e -> {
            try{
                contentView.getChildren().setAll(loadFXML("PlayView/PlayView.fxml"));
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        });

    }

    public Parent loadFXML(String fxmlViewPath) throws IOException {
        return FXMLLoader.load(getClass().getResource("/views/" + fxmlViewPath));
    }

    private void bindTabViewDimensions(Pane tabView, Pane container){
        ViewDimensionBinder.bindOneToOneDimension(
                tabView.minWidthProperty(),
                tabView.maxWidthProperty(),
                container.widthProperty()
        );

        ViewDimensionBinder.bindOneToOneDimension(
                tabView.minHeightProperty(),
                tabView.maxHeightProperty(),
                container.heightProperty().multiply(0.1)
        );
    }

    private void bindButtonDimensions(Pane tabView){
        for(Button button : tabView.getChildren().stream().filter(node -> node instanceof Button).toArray(Button[]::new)){
            ViewDimensionBinder.bindOneToOneDimension(
                    button.minHeightProperty(),
                    button.maxHeightProperty(),
                    tabView.heightProperty()
            );

            System.out.println("Bound button " + button.getText());
            ViewDimensionBinder.bindOneToOneDimension(
                    button.minWidthProperty(),
                    button.maxWidthProperty(),
                    tabView.widthProperty().multiply(0.25)
            );
        }
    }
}
