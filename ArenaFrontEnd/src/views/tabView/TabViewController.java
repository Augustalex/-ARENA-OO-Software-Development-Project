package views.tabView;

import arena.session.Session;
import arena.users.*;
import arena.users.advertiser.IAdvertiser;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import views.DimensionBinder;
import views.FXMLViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by August on 2016-12-08.
 */
public class TabViewController extends FXMLViewController implements Initializable{

    private FXMLViewController currentContentController = null;

    private ObjectProperty<FXMLViewController> currentController = new SimpleObjectProperty<>(null);
    private final Pane contentView;

    @FXML
    private HBox tabView;

    public TabViewController(Pane contentView){
        this.contentView = contentView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupLoginListener();
        setupLoginView();

        bindButtonDimensions(tabView);

        //setupTabViewRouting();
    }

    private void setupLoginListener(){
        Session.getSession().userProperty().addListener((observable, oldValue, newValue) -> {
            setButtonsForUserToTabView(newValue);
        });
    }

    private void setupLoginView() {
        try {
            contentView.getChildren().setAll(loadFXML("systemLogin/SystemLogin.fxml"));
        } catch (IOException e) {
            System.out.println("Could not load login screen.");
            e.printStackTrace();
        }
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

    //TODO fix create tournament, make a default choice for tournament style so that the box is not empty

    private RouterButton getSpectatorButton(){
        return
                new RouterButtonBuilder()
                        .buttonText("WATCH")
                        .fxmlPath("spectateMatch/spectateLobby/SpectateLobbyView.fxml")
                        .contentView(contentView)
                        .currentControllerProperty(currentController)
                    .build();
    }

    private RouterButton getPlayerButton(){
        return
                new RouterButtonBuilder()
                        .buttonText("PLAY")
                        .fxmlPath("PlayView/PlayView.fxml")
                        .contentView(contentView)
                        .currentControllerProperty(currentController)
                    .build();

    }

    private RouterButton getOperatorButton(){
        return
                new RouterButtonBuilder()
                        .buttonText("Handle Tournament Style")
                        .fxmlPath("tournament/handleTournamentStyle/HandleTournamentStyle.fxml")
                        .contentView(contentView)
                        .currentControllerProperty(currentController)
                    .build();
    }

    private RouterButton getLeagueOwnerButtons(){
        return
                new RouterButtonBuilder()
                        .buttonText("Create Tournament")
                        .fxmlPath("tournament/configureTournament/ConfigureTournamentView.fxml")
                        .contentView(contentView)
                        .currentControllerProperty(currentController)
                    .build();
    }

    private RouterButton getAdvertiserButtons(){
        return
                new RouterButtonBuilder()
                        .buttonText("Handle Advertisement")
                        .fxmlPath("handleAdvertisement/HandleAdvertisementsContainer.fxml")
                        .contentView(contentView)
                        .currentControllerProperty(currentController)
                    .build();
    }

    private RouterButton getLoginButton(){
        return
                new RouterButtonBuilder()
                        .buttonText("Profile")
                        .fxmlPath("systemLogin/SystemLogin.fxml")
                        .contentView(contentView)
                        .currentControllerProperty(currentController)
                    .build();
    }

    private List<RouterButton> getButtonsForUser(IUser user){

        List<RouterButton> buttons = new ArrayList<>();
        buttons.add(getSpectatorButton());

        if(user instanceof IPlayer)
            buttons.add(getPlayerButton());

        if(user instanceof IOperator) {
            buttons.add(getOperatorButton());

        }

        if(user instanceof IAdvertiser)
            buttons.add(getAdvertiserButtons());

        if(user instanceof ILeagueOwner)
            buttons.add(getLeagueOwnerButtons());

        return buttons;
    }

    private void setButtonsForUserToTabView(IUser user){
        addButtonsToTabView(getButtonsForUser(user));
    }

    private void addButtonsToTabView(List<RouterButton> buttons){
        Platform.runLater(() -> {
            tabView.getChildren().clear();
            contentView.getChildren().clear();
            buttons.forEach(button -> tabView.getChildren().add(button));
            tabView.getChildren().add(getLoginButton());
            bindButtonDimensions(tabView);
        });
    }


    /**
     * Setups loading of content by the buttons of the TabView.
     * @deprecated
     */
   /* private void setupTabViewRouting() {

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
    }*/

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
