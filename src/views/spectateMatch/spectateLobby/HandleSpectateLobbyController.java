package views.spectateMatch.spectateLobby;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * View controller for the Operators "Handle tournamentStyle" view. Sets up the TournamentStyle settings choices available, and sets
 * up routing for buttons to their corresponding "TournamentStyle" views.
 *
 * Extended ERROR handling for invalid input data needs to be fixed.
 */
public class HandleSpectateLobbyController implements Initializable{

    @FXML
    private Label test;

    @FXML
    private TableView tabletest;
    @FXML
    private Button gameButton;
    ObservableList observableList = FXCollections.observableArrayList(
            new Person("ProLeague", "BestOfFiveUltimate", "Yoda Vs Vader"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
    );
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureView();
    }

    private void configureView(){
        gameButton.setOnAction(event -> {
            test.setText("Hello world");
            setTableView();
        });
        //setListView();
    }
    public void setTableView(){
        tabletest.setEditable(true);
        TableColumn leagueNameCol = new TableColumn("League Name");
        TableColumn tournamentNameCol = new TableColumn("Tournament Name");
        TableColumn matchGamesCol = new TableColumn("MatchGame");
        leagueNameCol.setMinWidth(200);
        leagueNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        tournamentNameCol.setMinWidth(200);
        tournamentNameCol.setCellValueFactory( new PropertyValueFactory<Person, String>("lastName"));
        matchGamesCol.setMinWidth(200);
        matchGamesCol.setCellValueFactory( new PropertyValueFactory<Person, String>("email"));
        tabletest.setItems(observableList);
        tabletest.getColumns().clear();
        tabletest.getColumns().addAll(leagueNameCol, tournamentNameCol, matchGamesCol);
        tabletest.setEditable(false);
    }
    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }
    }
}