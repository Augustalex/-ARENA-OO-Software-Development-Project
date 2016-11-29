package views.spectateMatch.spectateLobby;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.DoubleToIntFunction;


/**
 * View controller for the Operators "Handle tournamentStyle" view. Sets up the TournamentStyle settings choices available, and sets
 * up routing for buttons to their corresponding "TournamentStyle" views.
 *
 * Extended ERROR handling for invalid input data needs to be fixed.
 */
public class HandleSpectateLobbyController implements Initializable{

    @FXML
    private TableView tabletest;
    @FXML
    private Button gameButton;
    @FXML
    private Button othello;
    @FXML
    private Button ticTacToe;
    @FXML
    private TableColumn leagueCol;
    @FXML
    private TableColumn tourNameCol;
    @FXML
    private TableColumn nrMatchesCol;
    @FXML
    private Label placeHolder;
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
            setTableView(observableList);
        });
        othello.setOnAction(event ->{
            placeHolder.setVisible(false);
            ObservableList data = FXCollections.observableArrayList(
                    new Person("ProLeague","UltimateShowdown","7 Matches"),
                    new Person("ProLeague", "UltimateLosers", "4 Matches")
            );
            setTableView(data);
        });
        ticTacToe.setOnAction(event -> {
            placeHolder.setVisible(false);
            ObservableList data = FXCollections.observableArrayList(
                    new Person("ProLeague", "Dancing","2 Matches"),
                    new Person("JediMaster", "JediTour", "4 Matches")
            );
            setTableView(data);
        });
        tabletest.setOnMousePressed(new EventHandler<MouseEvent>() {
            /**
             * Handles the mouseclick onto a row in the tableView.
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
                    Person person = (Person) row.getItem(); // Typecast för att få fram objektet
                    placeHolder.setVisible(true);
                    if (row.getIndex() % 2 ==  0) {
                        placeHolder.setTextFill(Paint.valueOf("blue"));
                        placeHolder.setText(person.getEmail()); // Byter namn på labeln till en cell rad höhöhö
                    }
                    else{
                        placeHolder.setTextFill(Paint.valueOf("crimson"));
                        placeHolder.setText(person.getEmail());

                    }
                }
            }
        });
        //setListView();
    }
    // Nedan är ifrån Oracle Doc skall bort /Björn
    private void setTableView(ObservableList hardCode){
        tabletest.setVisible(true);
        leagueCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        tourNameCol.setCellValueFactory( new PropertyValueFactory<Person, String>("lastName"));
        nrMatchesCol.setCellValueFactory( new PropertyValueFactory<Person, String>("email"));
        tabletest.setItems(hardCode);
        tabletest.getColumns().clear();
        tabletest.getColumns().addAll(leagueCol, tourNameCol, nrMatchesCol);
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