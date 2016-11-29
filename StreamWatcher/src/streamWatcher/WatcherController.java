package streamWatcher;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.scene.chart.PieChart;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import utils.CompressionUtils;

import java.awt.Dimension;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.DataFormatException;

/**
 * Created by August on 2016-11-28.
 */
public class WatcherController implements Initializable{

    @FXML
    private ImageView imageView;

    @FXML
    private BorderPane display;

    @FXML
    private StackPane stackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        imageView.fitWidthProperty().bind(display.widthProperty());
//        imageView.setPreserveRatio(true);
//        watch(imageView, "192.168.1.4", 2000);
//
        Canvas canvas = new Canvas(1000,1000);

        watchOnCanvas(canvas, "192.168.1.4", 2000);

        stackPane.getChildren().add(canvas);
    }

    private void watchOnCanvas(Canvas canvas, String host, int port) {
        new Thread(() -> {
            try {
                Socket socket = new Socket(host, port);
                System.out.println("Got connection.");
                GraphicsContext context = canvas.getGraphicsContext2D();

                while(true) {
                    ObjectProperty<WritableImage> writableImageProperty = getCompressedImageFromStream(socket.getInputStream());

                    writableImageProperty.addListener((observable, oldValue, newValue) -> {
                        Platform.runLater(() -> context.drawImage(writableImageProperty.get(), 0,0));
                    });
                }
            } catch (IOException | ClassNotFoundException | DataFormatException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void watch(ImageView display, String host, int port){
        new Thread(() -> {
            try {
                System.out.println("Trying to connect...");
                Socket socket = new Socket(host, port);
                System.out.println("Connected! " + host + ":" + port);
                InputStream inputStream = socket.getInputStream();

                while(true) {
                    ObjectProperty<WritableImage> wImage = getCompressedImageFromStream(inputStream);
                    wImage.addListener(i -> {
                        WritableImage image = wImage.get();
                        display.setImage(image);
                    });
                }
            } catch (IOException | ClassNotFoundException | DataFormatException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private WritableImage getImageFromStream(InputStream inputStream) throws IOException, ClassNotFoundException {

        Dimension dimension = (Dimension) new ObjectInputStream(inputStream).readObject();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] inputBuffer = new byte[dimension.width * dimension.height * 4];
        int[] intBuffer = new int[dimension.width * dimension.height];
        int index = 0;
        boolean eof = false;
        while(!eof) {
            try {
//                inputBuffer[index++] = dataInputStream.readByte();
                intBuffer[index++] = dataInputStream.readInt();
                if(index == intBuffer.length)
                    throw new EOFException();
            } catch (EOFException ex) {
                eof = true;
            }
        }

        WritableImage image = new WritableImage(dimension.width, dimension.height);

        PixelWriter pixelWriter = image.getPixelWriter();
        pixelWriter.setPixels(0,0,dimension.width, dimension.height, PixelFormat.getIntArgbInstance(), intBuffer, 0, dimension.width);

        return image;
    }

    private ObjectProperty<WritableImage> getCompressedImageFromStream(InputStream inputStream) throws IOException, ClassNotFoundException, DataFormatException {
        Dimension imageSize = (Dimension) new ObjectInputStream(inputStream).readObject();

        DataInputStream dataInputStream = new DataInputStream(inputStream);

        int bufferSize = dataInputStream.readInt();
        byte[] inputBuffer = new byte[bufferSize];

        int index = 0;
        while(index < inputBuffer.length)
            inputBuffer[index++] = dataInputStream.readByte();

        return decompressImageData(inputBuffer, imageSize);
    }

    public ObjectProperty<WritableImage> decompressImageData(byte[] imageData, Dimension imageSize){
        ObjectProperty<WritableImage> future = new SimpleObjectProperty<>();

        new Thread(() -> {
            byte[] imageBuffer = new byte[0];
            try {
                imageBuffer = CompressionUtils.decompress(imageData);
                WritableImage image = new WritableImage(imageSize.width, imageSize.height);

                PixelWriter pixelWriter = image.getPixelWriter();
                pixelWriter.setPixels(0,0,imageSize.width, imageSize.height, PixelFormat.getByteBgraInstance(), imageBuffer, 0, imageSize.width*4);

                future.set(image);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DataFormatException e) {
                e.printStackTrace();
            }
        }).start();

        return future;
    }

}