package boardGameLibrary.viewModel.animation;

import boardGameLibrary.viewModel.gameBoard.cellMarker.CellMarker;
import boardGameLibrary.viewModel.gameBoard.cellMarker.MarkerLayer;
import boardGameLibrary.viewModel.gameBoard.cellMarker.MarkerToParentTuple;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * Created by August on 2016-10-22.
 */
public class MarkerAnimator {

    private CellMarker outGoing;
    private CellMarker inGoing;

    private Duration outTime;
    private Duration inTime;

    private MarkerLayer markerLayer;

    public void setMarkerLayer(MarkerLayer markerLayer){
        this.markerLayer = markerLayer;
    }

    public void setActors(CellMarker outGoing, CellMarker inGoing){
        this.outGoing = outGoing;
        this.inGoing = inGoing;
    }

    public void setAnimationTiming(int outTimeMillis, int inTimeMillis){
        this.outTime = Duration.millis(outTimeMillis);
        this.inTime = Duration.millis(inTimeMillis);
    }

    public void playAnimation(){
        markerLayer.getChildren().clear();

        final Timeline outTimeline = ReplaceAnimationToolkit.animateScaleOutX(this.outGoing.getShape(), this.outTime);
        final Timeline inTimeline = ReplaceAnimationToolkit.animateScaleInX(this.inGoing.getShape(), this.inTime);

        outTimeline.setOnFinished(e -> {
            markerLayer.addMarker(this.inGoing);
            MarkerToParentTuple.create(this.inGoing, this.markerLayer).bindDimensions(0.85);
            inTimeline.play();
        });

        markerLayer.addMarker(this.outGoing);
        outTimeline.play();
    }

    public void playAnimationThen(EventHandler<ActionEvent> eventHandler){
        final Timeline outTimeline = ReplaceAnimationToolkit.animateScaleOutX(this.outGoing.getShape(), this.outTime);
        final Timeline inTimeline = ReplaceAnimationToolkit.animateScaleOutX(this.inGoing.getShape(), this.inTime);

        outTimeline.setOnFinished(e -> {
            markerLayer.addMarker(this.inGoing);
            inTimeline.play();
        });

        inTimeline.setOnFinished(eventHandler);

        markerLayer.getChildren().clear();
        markerLayer.addMarker(this.outGoing);
        outTimeline.play();
    }
}
