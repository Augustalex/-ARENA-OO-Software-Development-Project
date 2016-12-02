package views.tournament.extendedTournamentView;

import arena.games.gameInformation.GameInformation;
import arena.tournament.ITournament;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.FXMLViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Simon on 01/12/2016.
 */
public class ExtendedTournamentViewController extends FXMLViewController {

    private GameInformation gameInformation;
    private ITournament tournament;

    @FXML
    private VBox gameInfoContainer;
    @FXML
    private Label TournamentName;

    @FXML
    private Label Matches;

    @FXML
    private VBox MatchList;

    @FXML
    private Label GameInfoLabel;

    @FXML
    private Label GameName;

    @FXML
    private Rectangle gameImage;

    public ExtendedTournamentViewController(ITournament tournament){
        this.tournament = tournament;
        this.gameInformation = tournament.getGameInfomation();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TournamentName.setText(tournament.getTournamentMetaInformation().getName());
        Matches.setText("Matches");

        gameImage.setFill(new ImagePattern(new Image(gameInformation.getImageURL())));
        gameImage.widthProperty().bind(gameInfoContainer.widthProperty().multiply(0.2));
        gameImage.heightProperty().bind(gameInfoContainer.widthProperty().multiply(0.2));
        GameName.setText(gameInformation.getGameName());
        GameInfoLabel.setText(gameInformation.getGameDescription());


    }

    @Override
    public void closeView() {
        System.out.println("LOL");
    }

}
