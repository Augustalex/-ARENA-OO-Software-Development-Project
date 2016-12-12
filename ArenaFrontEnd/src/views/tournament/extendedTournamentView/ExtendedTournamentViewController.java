package views.tournament.extendedTournamentView;

import arena.games.gameInformation.GameInformation;
import arena.tournament.ITournament;
import arena.tournament.match.IMatch;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import views.DimensionBinder;
import views.FXMLViewController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Simon on 01/12/2016.
 */
public class ExtendedTournamentViewController extends FXMLViewController {

    private GameInformation gameInformation;
    private ITournament tournament;
    private List<IMatch> listOfMatches;

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
        this.gameInformation = tournament.getGameInformation();
        this.listOfMatches = tournament.getMatches();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TournamentName.setText(tournament.getTournamentMetaInformation().getName());

        gameImage.setFill(new ImagePattern(new Image(gameInformation.getImageURL())));
        gameImage.widthProperty().bind(gameInfoContainer.widthProperty().multiply(0.2));
        gameImage.heightProperty().bind(gameInfoContainer.widthProperty().multiply(0.2));
        GameName.setText(gameInformation.getGameName());
        GameInfoLabel.setText(gameInformation.getGameDescription());

        System.out.println("No of matches: " + listOfMatches.size());
        for(IMatch match : tournament.getMatches()) {
            matchListHandler(match);
        }

    }

    private void matchListHandler(IMatch match){
        DimensionBinder.bindWidthToPercentageOfContainer(MatchList, 0.8, gameInfoContainer);
        try {
            Region matchListPane = loadNewMatchList(match);
            DimensionBinder.bindWidthToPercentageOfContainer(matchListPane, 0.95, MatchList);
            MatchList.getChildren().add(matchListPane);
            System.out.println("Added " + matchListPane + " to list.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Region loadNewMatchList(IMatch match) throws IOException {
        return (Region) this.loadFXML(
                getClass().getResource(
                        "/views/tournament/extendedTournamentView/MatchList.fxml"),
                c -> new MatchList(match)
        );
    }

    @Override
    public void closeView() {
        System.out.println("LOL");
    }

}
