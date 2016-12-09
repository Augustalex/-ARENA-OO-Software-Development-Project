package views.handleAdvertisement.handleExistingAdvertisements;

import arena.advertisement.ad.IPreferredAd;
import arena.advertisement.adRepository.AdRepository;
import arena.users.advertiser.Advertiser;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import views.handleAdvertisement.handleNewAdvertisement.HandleAdvertisementController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Johan on 2016-12-03.
 */
public class HandleAdvertisementMainViewController implements Initializable {
    //private List<AdvertisementMock> identifiedAds;
    private List<IPreferredAd> advertisements;
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
    }

    public Button getButton(){
        return addNewButton;
    }

    private void listAdvertisements() {
        //identifiedAds = AdRepositoryMock.getAdRepositoryMock().getAds(1);
        advertisements = AdRepository.get().getAdsFromOwner(new Advertiser(1));
        currentAdvertisements.getChildren().clear();
        row = 0;
        init();
        if(advertisements.size() == 0){
            currentAdvertisements.add(createNewCell("No active Ads"),0,row);
            return;
        }
        for(IPreferredAd ad: advertisements){
            currentAdvertisements.add(createNewCell(ad.getMetaInformation().getName()),0,row);
            currentAdvertisements.add(createNewCell(ad.getAdPreference().getPreferenceId()),1,row);
            currentAdvertisements.add(createNewCell(String.valueOf(ad.getMetaInformation().getAmount())),2,row);
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
            Optional<ButtonType> result = showConfirmation();
            if(result.get() == ButtonType.OK) {
                int index = GridPane.getRowIndex(delete);
                AdRepository.get().removeAd(advertisements.get(index - 1));
                //AdRepositoryMock.getAdRepositoryMock().removeAd(index-1);
                listAdvertisements();
            }
        });
        return delete;
    }

    private Optional<ButtonType> showConfirmation() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Delete confirmation");
        confirmation.setHeaderText("Your about to delete the advertisement");
        confirmation.setContentText("The advertisement will be removed from\nselected preference queue." +
                "\nPress OK to proceed.");
        return confirmation.showAndWait();
    }
}
