package boardGameLibrary.viewModel.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Created by August on 2016-10-22.
 */
public class ReplaceAnimationToolkit{

    public static Timeline animateScaleOutX(Node node, Duration duration) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        node.scaleXProperty().set(1);
        final KeyValue kv = new KeyValue(node.scaleXProperty(), 0);
        final KeyFrame kf = new KeyFrame(duration, kv);
        timeline.getKeyFrames().add(kf);

        return timeline;
    }

    public static Timeline animateScaleInX(Node node, Duration duration){
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        node.scaleXProperty().set(0);
        final KeyValue destinationKeyValue = new KeyValue(node.scaleXProperty(), 1);
        final KeyFrame endFrame = new KeyFrame(duration, destinationKeyValue);
        timeline.getKeyFrames().add(endFrame);

        return timeline;
    }
}
