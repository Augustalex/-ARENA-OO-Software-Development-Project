package streamWatcher;

import com.google.gson.Gson;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import hostProviderService.Host;
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
import javafx.util.Pair;
import org.apache.http.client.fluent.Request;
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

    private Object key = new Object();
    private long latestTime = 0;

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

        //watchOnCanvas(canvas, "192.168.1.4", 1995);

        Host liveStream = null;
        try {
            liveStream = new Gson().fromJson(Request.Get("http://172.20.10.3:2016")
                    .execute().returnContent().asString(), Host.class);
        } catch (IOException e) {
            System.out.println("Could not send request.");
            e.printStackTrace();
        }

            /*String body = Request.Get(liveStream.getURL())
                    .execute().returnContent().asString();


            Host nHost = new Gson().fromJson(body, Host.class);*/
            System.out.println(liveStream.getURL());
            //System.out.println(nHost.getURL());
            watchOnCanvas(canvas, liveStream.address, liveStream.port);

        stackPane.getChildren().add(canvas);
    }

    private void watchOnCanvas(Canvas canvas, String host, int port) {
        new Thread(() -> {
            try {

                Socket socket = new Socket(host, port);
                System.out.println("Got connection.");
                GraphicsContext context = canvas.getGraphicsContext2D();

                new Thread(() -> {
                    while(true) {
                        try {
                            Pair<Long, ObjectProperty<WritableImage>> timeStampedImage = getCompressedImageFromStream(socket.getInputStream());
                            System.out.println("GOT IMAGE " + timeStampedImage.getKey());

                            if(setIfFresh(timeStampedImage.getKey())){
                                ObjectProperty<WritableImage> writableImageProperty = timeStampedImage.getValue();
                                writableImageProperty.addListener((observable, oldValue, newValue) -> {
                                    Platform.runLater(() -> {
                                        context.drawImage(writableImageProperty.get(), 0,0);
                                        System.out.println("PRINTED IMAGE " + timeStampedImage.getKey());
                                    });
                                });
                            }
                            else
                                System.out.println("Disposed " + timeStampedImage.getKey());

                        } catch (IOException | DataFormatException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (IOException e) {
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
                    Pair<Long, ObjectProperty<WritableImage>> wImage = getCompressedImageFromStream(inputStream);

                    wImage.getValue().addListener(i -> {
                        WritableImage image = wImage.getValue().get();
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

    private Pair<Long, ObjectProperty<WritableImage>> getCompressedImageFromStream(InputStream inputStream) throws IOException, ClassNotFoundException, DataFormatException {
        Dimension imageSize = (Dimension) new ObjectInputStream(inputStream).readObject();

        DataInputStream dataInputStream = new DataInputStream(inputStream);

        int bufferSize = dataInputStream.readInt();
        long timeStamp = dataInputStream.readLong();

        byte[] inputBuffer = new byte[bufferSize];

        int index = 0;
        while(index < inputBuffer.length)
            inputBuffer[index++] = dataInputStream.readByte();

        return new Pair<>(timeStamp, decompressImageData(inputBuffer, imageSize));
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
            } catch (IOException | DataFormatException e) {
                e.printStackTrace();
            }
        }).start();

        return future;
    }

    private boolean setIfFresh(long timeStamp){
        synchronized (key){
            if(timeStamp > this.latestTime) {
                this.latestTime = timeStamp;
                return true;
            }
            else
                return false;
        }
    }

}