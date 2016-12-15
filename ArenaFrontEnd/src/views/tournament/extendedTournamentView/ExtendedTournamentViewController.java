package views.tournament.extendedTournamentView;

import arena.games.gameInformation.GameInformation;
import arena.tournament.ITournament;
import arena.tournament.leaderboard.ILeaderboard;
import arena.tournament.match.IMatch;
import arena.users.IPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import views.DimensionBinder;
import views.FXMLViewController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ResourceBundle;

/**
 * Created by Simon on 01/12/2016.
 */
public class ExtendedTournamentViewController extends FXMLViewController {

    private GameInformation gameInformation;
    private ITournament tournament;
    private List<IMatch> listOfMatches;
    private ILeaderboard leaderboardList;

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

    @FXML
    private VBox leaderboard;


    public ExtendedTournamentViewController(ITournament tournament){
        this.tournament = tournament;
        this.gameInformation = tournament.getGameInformation();
        this.listOfMatches = tournament.getMatches();
        this.leaderboardList = tournament.getLeaderboard();
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
        System.out.println("No of players: " + tournament.getAppliedPlayerList().length());
        for(IMatch match : tournament.getMatches()) {
            matchListHandler(match);
        }
        for(int i = 0; i < tournament.getAppliedPlayerList().length(); i++){
            leaderBoardHandler(tournament.getAppliedPlayerList().getPlayerFromList(i), i+1);
        }
    }

    private void leaderBoardHandler(IPlayer player, int pos){
        DimensionBinder.bindWidthToPercentageOfContainer(leaderboard, 0.8, gameInfoContainer);
        try{
            Region leaderBoardItem = loadNewLeaderBoardItem(player, pos);
            DimensionBinder.bindWidthToPercentageOfContainer(leaderBoardItem, 0.95, leaderboard);
            leaderboard.getChildren().add(leaderBoardItem);
        }catch(IOException e1){
            e1.printStackTrace();
        }
    }

    private Region loadNewLeaderBoardItem(IPlayer player, int pos) throws IOException{
        return (Region) this.loadFXML(
                getClass().getResource(
                        "/views/tournament/extendedTournamentView/Leaderboard.fxml"),
                c -> new LeaderBoard(player, pos)
                );
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
