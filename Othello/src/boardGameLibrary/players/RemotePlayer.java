package boardGameLibrary.players;

import boardGameLibrary.boardGame.board.BoardMoveMaker;
import boardGameLibrary.boardGame.match.propertyWrappers.MoveProperties;
import boardGameLibrary.boardGame.move.Move;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by August on 2016-09-30.
 */
public class RemotePlayer extends Player {

    private int port;
    private String ip;

    public RemotePlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public void makeMove(BoardMoveMaker boardMoveMaker, MoveProperties moveProperties) {
        new Thread(() -> {

            ServerSocket server = null;
            Socket socket = null;
            ObjectInputStream inputStream = null;
            try{
                server = new ServerSocket(port);
                System.out.println("Waiting for socket accepts on " + server.getInetAddress().getHostAddress() + ":" + port);

                socket = server.accept();
                System.out.println("Got a connection!");
                inputStream = new ObjectInputStream(socket.getInputStream());


                Move move = (Move) inputStream.readObject();
                System.out.println("Read a move.");
                Platform.runLater(() -> boardMoveMaker.makeMove(this, move));
                System.out.println("Applied move.");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (server != null)
                        server.close();
                    if (socket != null)
                        socket.close();
                    if (inputStream != null)
                        inputStream.close();
                }
                catch (Exception e){
                    System.out.println("Could not close server sockets and etc.");
                    e.printStackTrace();
                }
            }

        }).start();
    }

    public RemotePlayer setIP(String ipv4){
        this.ip = ipv4;
        return this;
    }

    public RemotePlayer setPort(int port){
        this.port = port;
        return this;
    }
}
