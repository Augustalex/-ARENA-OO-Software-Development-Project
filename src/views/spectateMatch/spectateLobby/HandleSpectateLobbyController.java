package views.spectateMatch.spectateLobby;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private ListView listtest;

    @FXML
    private TableView tabletest;
    ObservableList observableList = FXCollections.observableArrayList("chocolate", "salmon", "gold", "coral", "darkorchid",
            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "brown");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureView();
    }

    private void configureView(){
        test.setText("Hello world");
        setTableView();
        //setListView();
    }
    public void setTableView(){
        tabletest.setEditable(true);
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");
        tabletest.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
    }
    private void setListView()
    {
        listtest.setItems(observableList);
        listtest.setCellFactory(new Callback<ListView<String>,
                        ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listtest) {
                return new Cell();
            };
        });
    }
    static class Cell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Label rect1 = new Label();
            Label rect2 = new Label();
            HBox box = new HBox();
            if (item != null) {
                rect1.setText(item);
                rect2.setText(" YoloSwag");
                box.getChildren().setAll(rect1, rect2);
                setGraphic(box);
            }

        }
    }
}