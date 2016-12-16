package liveStream;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.util.Pair;
import utils.CompressionUtils;

import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Created by August on 2016-11-29.
 */
public class LiveStream {

    private final Object key = new Object();

    private boolean streamOn = false;
    private int port;
    private Node streamNode;

    private LiveStreamConfiguration config = new LiveStreamConfiguration();

    private final Object latestSnapshotKey = new Object();
    private Pair<Long, Pair<Dimension, byte[]>> latestSnapshot = new Pair<Long, Pair<Dimension, byte[]>>(0L, null);

    public LiveStream(Node node, int port){
        this.streamNode = node;
        this.port = port;
    }

    public void stream(){
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(port);

                ExecutorService executorService = Executors.newFixedThreadPool(getConfig().viewersCapacity);

                streamOn = true;
                while(streamOn){
                    Socket socket = server.accept();
                    executorService.submit(() -> streamToClient(streamNode, socket));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void smartStream(){
        startSnapshotInterval(streamNode, 40);

        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(port);

                ExecutorService executorService = Executors.newFixedThreadPool(getConfig().viewersCapacity);

                streamOn = true;
                while(streamOn){
                    Socket socket = server.accept();
                    executorService.submit(() -> smartStreamToClient(socket));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stopStream(){
        this.streamOn = false;
    }

    public LiveStreamConfiguration getConfiguration(){
        return this.config;
    }

    public void setConfiguration(LiveStreamConfiguration configuration){
        this.config = configuration;
    }

    private void startSnapshotInterval(Node node, int interval){
        intervalSnapshots(
                node,
                writableImage -> {
                    compressAndStore(System.currentTimeMillis(), writableImage);
                },
                interval
        );
    }

    private void compressAndStore(long timeStamp, WritableImage snapshot){
        new Thread(() -> {
            try {
                Pair<Dimension, byte[]> compressedImage = compressImage(snapshot);
                storeIfFresh(timeStamp, compressedImage);
            } catch (IOException e) {
                System.out.println("Could not compress image.");
                e.printStackTrace();
            }
        }).start();
    }

    private void storeIfFresh(Long timeStamp, Pair<Dimension, byte[]> compressedImage){
        synchronized (latestSnapshotKey){
            if(timeStamp > latestSnapshot.getKey()){
                this.latestSnapshot = new Pair<>(timeStamp, compressedImage);
                System.out.println("Set " + timeStamp);
            }
            else
                System.out.println("Disposed " + timeStamp);
        }
    }

    private Pair<Long, Pair<Dimension,byte[]>> getLatestSnapshot(){
        synchronized (latestSnapshotKey){
            return latestSnapshot;
        }
    }

    private void smartStreamToClient(Socket socket){
        while(streamOn){
            sendLatestSnapshot(socket);
        }
    }

    private void streamToClient(Node node, Socket socket){
        intervalSnapshots(node, snapshot -> compressAndSendImage(snapshot, socket), 100);
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

    private void sendLatestSnapshot(Socket socket){
        try{
            Pair<Long, Pair<Dimension, byte[]>> content = getLatestSnapshot();

            ObjectOutputStream objectWriter = new ObjectOutputStream(socket.getOutputStream());
            objectWriter.writeObject(content.getValue().getKey());

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            /*
                The protocol is:
                    int - size of data to be sent
                    long - time stamp
                    byte[] - bytes of images
             */

            byte[] data = content.getValue().getValue();
            int dataSize = data.length;
            dataOutputStream.writeInt(dataSize);
            dataOutputStream.writeLong(System.currentTimeMillis());

            for (int i = 0; i < dataSize; i++) {
                if (i == dataSize * 0.25 || i == dataSize * 0.5 || i == dataSize * 0.75 || i == dataSize * 0.9)
                    System.out.println(i + " of " + dataSize + " sent");
                dataOutputStream.writeByte(data[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void sendCompressedImageWithTrueSize(byte[] compressedBytes, Dimension dimension, OutputStream outputStream) throws IOException {
        synchronized (key) {
            ObjectOutputStream objectWriter = new ObjectOutputStream(outputStream);
            objectWriter.writeObject(dimension);

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            /*
                The protocol is:
                    int - size of data to be sent
                    long - time stamp
                    byte[] - bytes of images
             */
            dataOutputStream.writeInt(compressedBytes.length);
            dataOutputStream.writeLong(System.currentTimeMillis());

            for (int i = 0; i < compressedBytes.length; i++) {
                if (i == compressedBytes.length * 0.25 || i == compressedBytes.length * 0.5 || i == compressedBytes.length * 0.75 || i == compressedBytes.length * 0.9)
                    System.out.println(i + " of " + compressedBytes.length + " sent");
                dataOutputStream.writeByte(compressedBytes[i]);
            }

            //System.out.println("Sent image");
        }
    }

    private Pair<Dimension, byte[]> compressImage(WritableImage image) throws IOException{
        PixelReader pixelReader = image.getPixelReader();

        byte[] buffer = new byte[(int) (image.getWidth() * image.getHeight()) * 4];
        pixelReader.getPixels(0, 0, (int) (image.getWidth()), (int) (image.getHeight()), PixelFormat.getByteBgraInstance(), buffer, 0, (int) image.getWidth()*4);

        return new Pair<>(
                new Dimension((int)image.getWidth(), (int)image.getHeight()),
                CompressionUtils.compress(buffer)
        );
    }

    private byte[] compress(WritableImage image) throws IOException {
        PixelReader pixelReader = image.getPixelReader();

        byte[] buffer = new byte[(int) (image.getWidth() * image.getHeight()) * 4];
        pixelReader.getPixels(0, 0, (int) (image.getWidth()), (int) (image.getHeight()), PixelFormat.getByteBgraInstance(), buffer, 0, (int) image.getWidth()*4);

        return CompressionUtils.compress(buffer);
    }

    public LiveStreamConfiguration getConfig(){
        return this.config;
    }
}
