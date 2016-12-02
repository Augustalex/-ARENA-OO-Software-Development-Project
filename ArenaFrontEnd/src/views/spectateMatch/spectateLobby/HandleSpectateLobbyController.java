package views.spectateMatch.spectateLobby;

import arena.games.game.IGame;
import arena.games.gameInformation.GameInformation;
import arena.games.gameManager.ArenaGameManager;
import arena.league.ILeague;
import arena.tournament.ITournament;
import com.sun.org.apache.bcel.internal.generic.RET;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import spectate.SpectateTable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * View controller for the Operators "Handle tournamentStyle" view. Sets up the TournamentStyle settings choices available, and sets
 * up routing for buttons to their corresponding "TournamentStyle" views.
 *
 * Extended ERROR handling for invalid input data needs to be fixed.
 */
public class HandleSpectateLobbyController implements Initializable {

    @FXML
    private TableView tableTournaments;
    @FXML
    private TableColumn leagueCol;
    @FXML
    private TableColumn tourNameCol;

    @FXML
    private TableView tournamentMatchTable;

    @FXML
    private Button othello;
    @FXML
    private Button ticTacToe;
    @FXML
    private Label placeHolder;
    @FXML
    private TableColumn gameCol;
    @FXML
    private TableView tableGames;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureView();
    }

    private void configureView() {
        setTableGames();
        tableGames.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("HEJ HEJ");
            }
        });
        othello.setOnAction(event -> {
            placeHolder.setVisible(false);
            tournamentMatchTable.setVisible(false);
            setTableView();
        });

        ticTacToe.setOnAction(event -> {
            placeHolder.setVisible(false);
            tournamentMatchTable.setVisible(false);
            setTableView(); //hej
        });
        tableTournaments.setOnMousePressed(new EventHandler<MouseEvent>() {
            /**
             * Handles the mouseclick onto a row in the tableTournamentView.
             * The clicking on a row will trigger another event.
             * @param event
             */
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    Node node = ((Node) event.getTarget()).getParent();
                    TableRow row;
                    if (node instanceof TableRow) {
                        row = (TableRow) node;
                    } else {
                        // clicking on text part
                        row = (TableRow) node.getParent();
                    }
                    LeagueSettings league = (LeagueSettings) row.getItem(); // Typecast för att få fram objektet
                    System.out.println(league.getTournament());
                    try {
                        newView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //tableViewMatch(league, row);
                }
            }
        });
    }
    private void setTableGames(){
        tableGames.refresh();
        ObservableList<IGame> gameList = FXCollections.observableArrayList();

        for(ILeague league : SpectateTable.get().getLeagues())
            if(!gameList.contains(league.getGame()))
                gameList.add(league.getGame());

        gameCol.setCellValueFactory(new PropertyValueFactory<IGame, String>("gameInformation"));
        tableGames.setItems(gameList);
        tableGames.getColumns().clear();
        tableGames.getColumns().addAll(gameCol);
    }
    private void newView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/tournament/extendedTournamentView/ExtendedTournamentView.fxml"));
        Parent parent = loader.load();
        stage.setScene(new Scene(parent, 1600, 1000));
        stage.show();
    }

    private void setTableView() {
        ObservableList<LeagueSettings> tournamentsList = FXCollections.observableArrayList();

        for(ILeague league : SpectateTable.get().getLeagues())
            for(ITournament tournament : league.getTournaments())
                tournamentsList.add(new LeagueSettings(league, tournament, league.getGame()));

        tableTournaments.setVisible(true);
        leagueCol.setCellValueFactory(new PropertyValueFactory<LeagueSettings, ILeague>("league"));
        tourNameCol.setCellValueFactory(new PropertyValueFactory<LeagueSettings, ITournament>("tournament"));
        tableTournaments.setItems(tournamentsList);
        tableTournaments.getColumns().clear();
        tableTournaments.getColumns().addAll(leagueCol, tourNameCol);
    }

    public static class LeagueSettings {

        private ITournament tournament;
        private ILeague league;
        private IGame game;

        public LeagueSettings(ILeague league, ITournament tournament, IGame game){
            this.tournament = tournament;
            this.league = league;
            this.game = game;
        }

        public ILeague getLeague(){
            return this.league;
        }

        public ITournament getTournament() {
            return this.tournament;
        }
    }
}
