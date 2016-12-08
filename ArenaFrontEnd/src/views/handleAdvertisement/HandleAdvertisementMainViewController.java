package views.handleAdvertisement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import views.MainWindow.MainWindowController;
import views.handleAdvertisement.AdvertisementViewMocks.AdRepositoryMock;
import views.handleAdvertisement.AdvertisementViewMocks.AdvertisementMock;
import views.handleAdvertisement.handleNewAdvertisement.HandleAdvertisementController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Johan on 2016-12-03.
 */
public class HandleAdvertisementMainViewController implements Initializable {
    private List<AdvertisementMock> identifiedAds;
    private MainWindowController mainWindowController;
    int row = 0;
    @FXML
    private VBox mainAdvertisementView;

    @FXML
    private Text topicText;

    @FXML
    private GridPane currentAdvertisements;

    @FXML
    private Button addNewButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainAdvertisementView.setPadding(new Insets(20,500,0,15));
        listAdvertisements();
        addNewButton.setOnAction(e->{
            try {
                mainWindowController.closeView();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/handleAdvertisement/" +
                        "handleNewAdvertisement/HandleAdvertisement.fxml"));
                Parent parent = loader.load();
                HandleAdvertisementController controller = loader.getController();
                controller.setMainWindowController(mainWindowController);
                mainWindowController.getContentView().getChildren().setAll(parent);
            }catch(IOException ex){
                ex.printStackTrace();
            }
        });
    }

    public void setMainWindowController(MainWindowController mainWindowController){
        this.mainWindowController = mainWindowController;
    }

    private void listAdvertisements() {
        identifiedAds = AdRepositoryMock.getAdRepositoryMock().getAds(1);
        currentAdvertisements.getChildren().clear();
        row = 0;
        init();
        if(identifiedAds.size() == 0){
            currentAdvertisements.add(createNewCell("No active Ads"),0,row);
            return;
        }
        for(AdvertisementMock ad: identifiedAds){
            currentAdvertisements.add(createNewCell(ad.getName()),0,row);
            currentAdvertisements.add(createNewCell(ad.getPreference()),1,row);
            currentAdvertisements.add(createNewCell(String.valueOf(ad.getAmount())),2,row);
            currentAdvertisements.add(createDeleteButton(),3,row++);
        }
    }

    private void init() {
        currentAdvertisements.setHgap(20);
        currentAdvertisements.add(createNewCell("Ad Name"),0, row);
        currentAdvertisements.add(createNewCell("Preference"),1, row);
        currentAdvertisements.add(createNewCell("Ad Balance"),2,row);
        currentAdvertisements.add(createNewCell(""),3,row++);
    }

    private HBox createNewCell(String content){
        Text info = new Text(content);
        info.setFill(Color.WHITE);
        HBox cellContent = new HBox();
        cellContent.getChildren().add(info);
        cellContent.setAlignment(Pos.CENTER_LEFT);
        return cellContent;
    }

    private Button createDeleteButton(){
        Button delete = new Button("Delete");
        delete.setOnAction(e->{
            int index = GridPane.getRowIndex(delete);
            AdRepositoryMock.getAdRepositoryMock().removeAd(index-1);
            listAdvertisements();
        });
        return delete;
    }
}