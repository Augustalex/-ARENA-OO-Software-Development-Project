package advertisement.adDisplay;

import advertisement.ad.Ad;
import advertisement.adDisplay.exceptions.AdImageNotSet;
import advertisement.adSpot.AdSpot;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import views.DimensionBinder;

/**
 * Created by August on 2016-11-05.
 */
public class FxAdDisplay extends PaneAdDisplay {

    private StackPane innerPane = new StackPane();
    private ImageView adImageHolder = new ImageView();

    private AdvertHideButton hideButton = new AdvertHideButton();

    public FxAdDisplay(AdSpot adSpot){
        super(adSpot);

        this.setCenter(this.innerPane);
        this.innerPane.getChildren().add(adImageHolder);

        this.listenToCurrentAdChanges();
        this.adSpot.nextAd();

        this.showCloseButton();
        this.activateCloseButton();
    }

    public StackPane getInnerPane(){
        return this.innerPane;
    }

    @Override
    public AdDisplay setAsWideAd() {
        if(this.adImageHolder.getImage() == null)
            throw new AdImageNotSet();

        this.adImageHolder.fitHeightProperty().bind(this.heightProperty());

        DimensionBinder.bindOneToOneDimension(
                this.getInnerPane().minHeightProperty(),
                this.getInnerPane().maxHeightProperty(),
                this.heightProperty()
        );

        //Bind AdPane (the holder of the adverts) to the width of the image.
        DimensionBinder.bindOneToOneDimension(
                this.getInnerPane().minWidthProperty(),
                this.getInnerPane().maxWidthProperty(),
                this.adImageHolder.getImage().widthProperty().multiply(this.heightProperty().divide(this.adImageHolder.getImage().heightProperty()))
        );

        return this;
    }

    @Override
    public AdDisplay setAsTallAd() {
        if(this.adImageHolder.getImage() == null)
            throw new AdImageNotSet();

        //TODO Implement tall advert dimension bindings.

        return this;
    }

    @Override
    public void hideCloseButton() {
        this.getInnerPane().getChildren().remove(this.hideButton);
    }

    @Override
    public void showCloseButton() {
        this.hideButton.place(this);
    }

    @Override
    protected void displayAdContent(Ad ad){
        this.adImageHolder.setImage(ad.getImage());
    }

    private void activateCloseButton(){
        this.hideButton.stylize();
        this.hideButton.setHideOnAction();
    }
}
