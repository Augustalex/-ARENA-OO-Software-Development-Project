package subStream;

import hostProviderService.Host;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by August on 2016-12-15.
 */
public class SubStream {

    private static final int maxClients = 3;

    private final int serverPort;

    private boolean streamOn = false;

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    private final Object streamCountKey = new Object();
    private int streamCount = 0;

    public SubStream(int serverPort){
        this.serverPort = serverPort;
    }

    public boolean isFull(){
        return streamCount >= maxClients;
    }

    public void receiveStream(Host liveStreamDetails){
        try {
            Socket socket = new Socket(liveStreamDetails.address, liveStreamDetails.port);
            streamOn = true;
            System.out.println("STREAM TRUE!");
            stream(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopStream(){
        this.streamOn = false;
    }

    public boolean isRunning(){
        return streamOn;
    }

    public Host getStreamConnectionDetails(){
        try {
            return new Host(
                    Inet4Address.getLocalHost().getHostAddress(),
                    serverPort
            );
        } catch (UnknownHostException e) {
            System.out.println("Could not retrieve local host address.");
            e.printStackTrace();
            return new Host("0.0.0.0", serverPort);
        }
    }

    private void stream(Socket inputSocket) throws IOException {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(serverPort)){
                while(streamOn){
                    Socket outputSocket = serverSocket.accept();
                    increaseStreamCount();
                    new Thread(() -> {
                        try {
                            while (streamOn) {
                                System.out.println("Reading from " + inputSocket.getInetAddress().getHostAddress() + ":" + inputSocket.getPort());
                                System.out.println("Writing to " + outputSocket.getInetAddress().getHostAddress() + ":" + outputSocket.getPort());
                                outputSocket.getOutputStream().write(inputSocket.getInputStream().read());
                            }
                        } catch (Exception ex) {
                            System.out.println("Streaming ended.");
                        }
                        finally{
                            try {
                                outputSocket.close();
                                decreaseStreamCount();
                            } catch (IOException e) {
                                System.out.println("Could not close output socket.");
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void increaseStreamCount(){
        synchronized (streamCountKey){
            streamCount++;
        }
    }

    private void decreaseStreamCount(){
        synchronized (streamCountKey){
            streamCount--;
        }
    }
}
