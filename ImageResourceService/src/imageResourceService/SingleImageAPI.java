package imageResourceService;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import rest.Delivery;
import rest.PropertyDelivery;
import rest.ReST;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by August on 2016-12-01.
 */
public class SingleImageAPI extends ReST {

    private final ImageService imageService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public SingleImageAPI(ImageService service){
        this.imageService = service;
    }

    /**
     * Retrieve image with id specified at end of HTTP URI.
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onGet(HttpExchange httpExchange) throws Exception {
        executorService.submit(() ->{
            try {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                httpExchange.getResponseBody()
                        .write(
                                imageService.fromJavaFXImageToByteArray(
                                        imageService.getImage(getIdFromEndOfHttpURI(httpExchange))
                                )
                        );
            } catch (IOException e) {
                System.out.println("Could not send response content.");
                e.printStackTrace();
            }
        });
    }

    /**
     * A http post request is received and contains the JSON text of an ImageInfo class containing the
     * width, height and byte size of the Images that is about to be sent.
     *
     * Once the request is received and parsed a response is sent back telling the client that a
     * ServerSocket will be opened. The client will then have to connect to that socket by the
     * IP information they already have. The image will then be sent by the client as a
     * byte array. The size of the byte array is noted in the previously parsed ImageInfo class.
     *
     * When the entire image is through the image is processed and once a new ID has been
     * attached to the image by the ImageService, this ID is then sent back through the Socket
     * as a String.
     *
     * After the String is sent the ServerSocket is closed.
     *
     * This operation of receiving an image is synchronous and very costly to the host of the Image Service.
     *
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onPost(HttpExchange httpExchange) throws Exception {

        //TODO the socket cannot be opened on the same port as the HTTP server... This most be solved! When solved the Javadoc most be updated to reflect this change.
        try {
            String httpBody = getStringBodyFromHttpExchange(httpExchange);
            ImageInfo imageInfo = new Gson().fromJson(httpBody, ImageInfo.class);

            //How should it pertain a good and valid port to work on???
            ServerSocket serverSocket = new ServerSocket(2000);
            Socket socket = serverSocket.accept();

            byte[] imageData = new byte[imageInfo.byteDataSize];
            DataInputStream imageBytesInput = new DataInputStream(socket.getInputStream());

            int index = 0;
            while(index < imageInfo.byteDataSize)
                imageData[index++] = imageBytesInput.readByte();

            WritableImage writableImage = new WritableImage(imageInfo.width, imageInfo.height);

            writableImage.getPixelWriter().setPixels(
                    0,
                    0,
                    imageInfo.width,
                    imageInfo.height,
                    PixelFormat.getByteBgraInstance(),
                    imageData,
                    0,
                    imageInfo.width*4
            );

            String newImageId = imageService.storeImage(writableImage);

            //Send back the new Image ID that was recently generated.
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.print(newImageId);
            printWriter.close();

            socket.close();
            serverSocket.close();

            try {
                sendStringContentResponse(HttpURLConnection.HTTP_OK, newImageId, httpExchange);
            } catch (IOException e) {
                System.out.println("Could not send response content.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Could not send response content.");
            e.printStackTrace();
        }
    }

    /**
     * Delete image with id specified at the end of the HTTP URI.
     * @param httpExchange
     * @throws Exception
     */
    @Override
    public void onDelete(HttpExchange httpExchange) throws Exception {
        imageService.removeImage(getIdFromEndOfHttpURI(httpExchange));
    }

    @Override
    public void onPut(HttpExchange httpExchange) throws Exception {
        sendEmptyResponse(HttpURLConnection.HTTP_BAD_METHOD, httpExchange);
    }
}
