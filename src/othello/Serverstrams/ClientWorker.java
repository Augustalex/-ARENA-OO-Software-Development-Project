/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serverstrams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S132063
 */
public class ClientWorker implements Runnable {
    private Socket clientSocket = null;
    private String serverText = "Yolo mf!";
    ClientWorker(Socket clientSocket){
    this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        
        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output= clientSocket.getOutputStream();
            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nClientWorkerRunnable: " +
            this.serverText + " - " +
            time +
            "").getBytes());
            output.close();
            input.close();
            System.out.println("Request processed: " + time);
        } catch (IOException ex) {
            Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
}
