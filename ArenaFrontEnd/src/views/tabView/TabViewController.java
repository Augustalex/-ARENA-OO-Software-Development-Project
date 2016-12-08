package views.tabView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import views.DimensionBinder;
import views.FXMLViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-12-08.
 */
public class TabViewController extends FXMLViewController implements Initializable{

    private FXMLViewController currentContentController = null;

    private final Pane contentView;

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

    @FXML
    private Button loginButton;

    public TabViewController(Pane contentView){
        this.contentView = contentView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindButtonDimensions(tabView);
        setupTabViewRouting();
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/handleAdvertisement/HandleAdvertisementMain.fxml"));
                Parent parent = loader.load();
                contentView.getChildren().setAll(parent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        loginButton.setOnAction(e->{
            try {
                closeCurrentContentController();
                Parent parent = this.loadFXML("systemLogin/SystemLogin.fxml");
                contentView.getChildren().setAll(parent);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
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

    @Override
    public void closeView() {
        closeCurrentContentController();
    }
}
