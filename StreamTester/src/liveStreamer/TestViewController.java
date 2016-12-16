package liveStreamer;

import liveStream.LiveStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by August on 2016-12-15.
 */
public class TestViewController implements Initializable {

    @FXML
    private StackPane mainWindow;

    @FXML
    private Label clock;

    private LiveStream liveStream;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        followTime(clock, 100);

        liveStream = new LiveStream(mainWindow, 1995);
        liveStream.stream();

    }

    private void followTime(Label label, int updateInterval){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    label.setText(String.valueOf((System.currentTimeMillis())%60000));
                });
            }
        }, 0, updateInterval);
    }
}
