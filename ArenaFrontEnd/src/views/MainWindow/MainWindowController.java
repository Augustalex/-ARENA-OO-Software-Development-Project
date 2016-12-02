package views.MainWindow;

import views.FXMLViewController;
import views.DimensionBinder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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

    @FXML
    private Button createTournamentButton;

    @FXML
    private Button handleTournamentButton;

    @FXML
    private Button handleAdButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupContentView();
        setupTabView();

        setupTabViewRouting();
    }

    @Override
    public void closeView() {
        closeCurrentContentController();
    }

    /**
     * Setups loading of content by the buttons of the TabView.
     */
    private void setupTabViewRouting() {

        playButton.setOnAction(e -> {
            try {
                closeCurrentContentController();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PlayView/PlayView.fxml"));
                Parent parent = loader.load();
                this.currentContentController = loader.getController();

                contentView.getChildren().setAll(parent);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        createTournamentButton.setOnAction(e -> {
            try {
                closeCurrentContentController();
                Parent parent = this.loadFXML("tournament/configureTournament/ConfigureTournamentView.fxml");
                contentView.getChildren().setAll(parent);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        handleTournamentButton.setOnAction(e -> {
            try {
                closeCurrentContentController();
                Parent parent = this.loadFXML("tournament/handleTournamentStyle/HandleTournamentStyle.fxml");
                contentView.getChildren().setAll(parent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        watchButton.setOnAction(e -> {
            try {
                closeCurrentContentController();
                Parent parent = this.loadFXML("spectateMatch/spectateLobby/SpectateLobbyView.fxml");
                contentView.getChildren().setAll(parent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        handleAdButton.setOnAction(e->{
            try {
                closeCurrentContentController();
                Parent parent = this.loadFXML("handleAdvertisement/HandleAdvertisement.fxml");
                contentView.getChildren().setAll(parent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
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
        bindButtonDimensions(tabView);
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
                        mainWindow
                );
    }

    /**
     *  Bind dimensions of the buttons to its containing tabView.
     *  Dimension factors are defines in variables in the method.
     * @param tabView
     */
    private void bindButtonDimensions(Pane tabView){
        double widthFactor = 0.125;
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
        if(this.currentContentController != null)
            this.currentContentController.closeView();
    }

}
