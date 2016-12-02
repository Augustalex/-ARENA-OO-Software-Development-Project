package liveStreamer;

import com.idrsolutions.image.scale.QualityScaler;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import utils.CompressionUtils;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Created by August on 2016-11-28.
 */
public class MainViewController implements Initializable{

    Object key = new Object();

    @FXML
    private Label label;

    @FXML
    private BorderPane screen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        followTime(this.label, 1);
        stream(screen, 2000);
    }


    private void stream(Node node, int port){
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(port);

                ExecutorService executorService = Executors.newFixedThreadPool(100);

                while(true){
                    Socket socket = server.accept();
                    executorService.submit(() -> streamToClient(node, socket));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    private void streamToClient(Node node, Socket socket){
        intervalSnapshots(node, snapshot -> compressAndSendImage(snapshot, socket), 100);
    }

    private void compressAndSendImage(WritableImage image, Socket socket){
        new Thread(() -> {
            try {
                sendCompressedImageWithTrueSize(
                        compress(image),
                        new Dimension((int)image.getWidth(), (int)image.getHeight()),
                        socket.getOutputStream()
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void intervalSnapshots(Node node, Consumer<WritableImage> snapshotHandler, int interval){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    node.snapshot(param -> {
                        snapshotHandler.accept(param.getImage());
                        return null;
                    }, new SnapshotParameters(), null);
                });
            }
        }, 0, interval);
    }

    private Socket establishStream(int port) throws IOException {
        System.out.println("Establishing connection.");
        ServerSocket server = new ServerSocket(port);
        return server.accept();
    }

    private void sendImage(OutputStream outputStream, WritableImage image) throws IOException {
        synchronized (key) {

            PixelReader pixelReader = image.getPixelReader();

            int[] buffer = new int[(int) (image.getWidth() * image.getHeight())];
            Dimension dimension = new Dimension((int) image.getWidth(), (int) image.getHeight());
//            System.out.println("Dimension: " + dimension.width + ", " + dimension.height);

            ObjectOutputStream objectWriter = new ObjectOutputStream(outputStream);
            objectWriter.writeObject(dimension);

            pixelReader.getPixels(0, 0, (int) (image.getWidth()), (int) (image.getHeight()), PixelFormat.getIntArgbInstance(), buffer, 0, (int) image.getWidth());

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//            System.out.println("Buffer length: " + buffer.length);

            for (int i = 0; i < buffer.length; i++) {
                if (i == buffer.length * 0.25 || i == buffer.length * 0.5 || i == buffer.length * 0.75 || i == buffer.length * 0.9)
//                    System.out.println(i + " of " + buffer.length + " sent");
                dataOutputStream.writeInt(buffer[i]);
            }

//            System.out.println("Wrote " + image);
        }
    }

    private void sendCompressedImageWithTrueSize(byte[] compressedBytes, Dimension dimension, OutputStream outputStream) throws IOException {
        synchronized (key) {
            ObjectOutputStream objectWriter = new ObjectOutputStream(outputStream);
            objectWriter.writeObject(dimension);

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            dataOutputStream.writeInt(compressedBytes.length);

            for (int i = 0; i < compressedBytes.length; i++) {
             /*   if (i == compressedBytes.length * 0.25 || i == compressedBytes.length * 0.5 || i == compressedBytes.length * 0.75 || i == compressedBytes.length * 0.9)
                    System.out.println(i + " of " + compressedBytes.length + " sent");*/
                dataOutputStream.writeByte(compressedBytes[i]);
            }

            //System.out.println("Sent image");
        }
    }

    private byte[] compress(WritableImage image) throws IOException {
        PixelReader pixelReader = image.getPixelReader();

        byte[] buffer = new byte[(int) (image.getWidth() * image.getHeight()) * 4];
        pixelReader.getPixels(0, 0, (int) (image.getWidth()), (int) (image.getHeight()), PixelFormat.getByteBgraInstance(), buffer, 0, (int) image.getWidth()*4);

        return CompressionUtils.compress(buffer);
    }

    private WritableImage getScaledImage(WritableImage image, double factor) throws IOException {
        return SwingFXUtils.toFXImage(QualityScaler.getScaledImage(SwingFXUtils.fromFXImage(image, null), (int)(image.getWidth() * factor), (int)(image.getHeight()*factor)), null);
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
