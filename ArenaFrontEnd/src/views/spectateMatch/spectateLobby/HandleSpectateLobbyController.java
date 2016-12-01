package views.spectateMatch.spectateLobby;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.FXMLViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * View controller for the Operators "Handle tournamentStyle" view. Sets up the TournamentStyle settings choices available, and sets
 * up routing for buttons to their corresponding "TournamentStyle" views.
 *
 * Extended ERROR handling for invalid input data needs to be fixed.
 */
public class HandleSpectateLobbyController implements Initializable{

    @FXML
    private TableView tableTournaments;
    @FXML
    private TableColumn leagueCol;
    @FXML
    private TableColumn tourNameCol;
    @FXML
    private TableColumn nrMatchesCol;

    @FXML
    private TableView tournamentMatchTable;
    @FXML
    private TableColumn tournamentNameCol;
    @FXML
    private TableColumn matchCol;
    @FXML
    private TableColumn curScoreCol;
    @FXML
    private Button othello;
    @FXML
    private Button ticTacToe;
    @FXML
    private Label placeHolder;

    private static ArrayList<Match> mockGameList(){
        ArrayList<Match> test = new ArrayList<>();
        for( int i = 0; i < 4; i++) {
            Match match = new Match("Yoda", "hej +" +i);
            test.add(match);
        }
        return test;
    }
    ObservableList observableList = FXCollections.observableArrayList(
            new LeagueSettings("ProLeague", "BestOfFiveUltimate", "Yoda Vs Vader"),
            new LeagueSettings("Isabella", "Johnson", "isabella.johnson@example.com"),
            new LeagueSettings("Ethan", "Williams", "ethan.williams@example.com"),
            new LeagueSettings("Emma", "Jones", "emma.jones@example.com"),
            new LeagueSettings("Michael", "Brown", "michael.brown@example.com")
    );
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureView();
    }

    private void configureView(){
        othello.setOnAction(event ->{
            placeHolder.setVisible(false);
            tournamentMatchTable.setVisible(false);
            ObservableList data = FXCollections.observableArrayList(
                    new LeagueSettings("ProLeague","UltimateShowdown","7 Matches"),
                    new LeagueSettings("ProLeague", "UltimateLosers", "4 Matches")
            );
            setTableView(data);
        });
        ticTacToe.setOnAction(event -> {
            placeHolder.setVisible(false);
            tournamentMatchTable.setVisible(false);
            ObservableList data = FXCollections.observableArrayList(
                    new LeagueSettings("ProLeague", "Dancing","2 Matches"),
                    new LeagueSettings("JediMaster", "JediTour", "4 Matches")
            );
            setTableView(data);
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
                    if (row.getIndex() % 2 ==  0) {
                        ObservableList match = FXCollections.observableArrayList(
                                new LeagueSettings(league.getLeagueName(), "Timon vs Pumba","300"),
                                new LeagueSettings(league.getLeagueName(), "Luke vs Leia", "4")
                        );
                        setTableViewMatch(match);
                    }
                    else{
                        ObservableList match = FXCollections.observableArrayList(
                                new LeagueSettings(league.getLeagueName(), "Tarzan vs Jane","25"),
                                new LeagueSettings(league.getLeagueName(), "USA Vs Russia", "1")
                        );
                        setTableViewMatch(match);
                    }
                }
            }
        });

        tournamentMatchTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            /**
             * Handles the mouseclick onto a row in the tableTournamentLobbyView.
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

                    LeagueSettings league = (LeagueSettings) row.getItem();
                    try {
                        newView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //setListView();
    }
    private void newView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PlayView/PlayView.fxml"));
        Parent parent = loader.load();
        stage.setScene(new Scene(parent, 1600, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    private void setTableView(ObservableList hardCode){
        tableTournaments.setVisible(true);
        leagueCol.setCellValueFactory(new PropertyValueFactory<LeagueSettings, String>("leagueName"));
        tourNameCol.setCellValueFactory( new PropertyValueFactory<LeagueSettings, String>("tournamentName"));
        nrMatchesCol.setCellValueFactory( new PropertyValueFactory<LeagueSettings, String>("tournamentMatches"));
        tableTournaments.setItems(hardCode);
        tableTournaments.getColumns().clear();
        tableTournaments.getColumns().addAll(leagueCol, tourNameCol, nrMatchesCol);
    }
    private void setTableViewMatch(ObservableList match){
        tournamentMatchTable.setVisible(true);
        tournamentNameCol.setCellValueFactory(new PropertyValueFactory<LeagueSettings, String>("leagueName"));
        matchCol.setCellValueFactory(new PropertyValueFactory<LeagueSettings, String>("tournamentName"));
        curScoreCol.setCellValueFactory(new PropertyValueFactory<LeagueSettings, String>("tournamentMatches"));
        tournamentMatchTable.setItems(match);
        tournamentMatchTable.getColumns().clear();
        tournamentMatchTable.getColumns().addAll(tournamentNameCol,matchCol,curScoreCol);
    }

    // Nedan är ifrån Oracle Doc skall bort /Björn
    public static class LeagueSettings {

        private SimpleStringProperty leagueName;
        private SimpleStringProperty tournamentName;
        private SimpleStringProperty tournamentMatches;
        private ArrayList<Match> match = new ArrayList();

        private LeagueSettings(String fName, String lName, String tournamentMatches){
            this.leagueName = new SimpleStringProperty(fName);
            this.tournamentName = new SimpleStringProperty(lName);
            this.tournamentMatches = new SimpleStringProperty(tournamentMatches);
            this.match = mockGameList();
        }

        public String getLeagueName() {
            return leagueName.get();
        }

        public void setLeagueName(String fName) {
            leagueName.set(fName);
        }

        public String getTournamentName() {
            return tournamentName.get();
        }

        public void setTournamentName(String fName) {
            tournamentName.set(fName);
        }

        public String getTournamentMatches() {
            return tournamentMatches.get();
        }

        public void setTournamentMatches(String fName) {tournamentMatches.set(fName);}

    }

    private static class Match {
        private final SimpleStringProperty player;
        private final SimpleStringProperty score;
        private Match(String player, String score){
            this.player = new SimpleStringProperty(player);
            this.score = new SimpleStringProperty(score);
        }

        private String getScore() {
            return this.score.get();
        }
        private void setScore(String score){
            this.score.set(score);
        }

        private String getPlayer(){
            return this.player.get();
        }
        private void setPlayer(String player){
            this.player.set(player);
        }
    }
}
