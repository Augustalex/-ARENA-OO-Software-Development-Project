/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author S132063
 */
public abstract class Server {
    
    //Socket clientSocket = null;
    private final int serverPort = 4444;
    ServerSocket serverSocket;
    
    Server() throws IOException{
        this.serverSocket = new ServerSocket(serverPort);
    }
    
    public abstract void acceptConnections(ServerSocket serverSocket);
    
    public Socket getClientConnection(ServerSocket serverSocket) throws IOException{
                Socket clientSocket = serverSocket.accept();
                return clientSocket;
    }
}
