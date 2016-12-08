/*
 * OOMU - laboration 2: Othello
 * Johan - s152754, Patric - s153364 och Simon - s152428
 * Grupp 05
 */
package othello.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import othello.util.Point;

/**
 * Manage serverconnection
 * @author Patric
 */
public class ServerManager {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String serverIP;
    private Socket socket;
    private ArrayList<Point> moves;
    private Point point;
    private int serverPort;

    /**
     * Constructor: sets port and ip address.
     * @param host Hostaddress
     * @param port Portnumber
     */
    public ServerManager(String host, int port){
        serverIP = host;
        serverPort = port;
    }

    /**
     * Method for gameclient to connect to gameserver.
     * Sends list of availible moves to server for evaluation.
     * Recieve Point of best move
     */
    public void startClient() {
        try {
            //connectToServer();
            System.out.println("Try to connect...");
            socket = new Socket(InetAddress.getByName(serverIP), serverPort);
            System.out.println("Connected to server...on " + socket.getInetAddress().getHostName());

            //setupStreams();
            output = new ObjectOutputStream(socket.getOutputStream());
            output.flush();
            input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Streams are up...");

            //sendMessage
            sendMessage(moves);

            //readMessage();
            point = (Point) input.readObject();
            System.out.println(point.getPointI() + " " + point.getPointJ());

        } catch (IOException e) {
            System.out.println("Unable to connect to server...");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //close();
            System.out.println("Closing down..");
            try {
                output.close();
                input.close();
                socket.close();
            } catch (IOException | java.lang.NullPointerException e) {
                //e.printStackTrace();
            }
        }
    }
    
    /**
     * Method for getting point
     * @return Point of move
     */
    public Point getPoint(){
        return point;
    }

    /**
     * Method for sending message to server.
     * @param moves Arralist of possible moves
     */
    public void sendMessage(ArrayList<Point> moves){
        try {
            output.writeObject(moves);
            output.flush();
            System.out.println("Från SM: " + moves);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Method of setting arrayList with possible moves
     * @param moves List of possible moves
     */
    public void setList(ArrayList<Point> moves){
        this.moves = moves;
    }

}