/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testServer;

import java.io.*;
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
            String message;
            sendMessage(clientSocket,"HTTP/1.1 200 OK\n");
            recieveMessage(clientSocket);
            sendMessage(clientSocket, "You suck balls \n");
            recieveMessage(clientSocket);
            sendMessage(clientSocket, "Afraid of me yet? \n");
            recieveMessage(clientSocket);
            clientSocket.close();
            System.out.println("Request processed: ");
        } catch (IOException ex) {
            Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void sendMessage(Socket clientSocket, String message) throws IOException{
         
         OutputStream os = clientSocket.getOutputStream();
         OutputStreamWriter osw = new OutputStreamWriter(os);
         BufferedWriter bw = new BufferedWriter(osw);
         long time = System.currentTimeMillis();
         bw.write(message);
         bw.flush();
    }
    private void recieveMessage(Socket clientSocket) throws IOException{
        InputStream is = clientSocket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String message = br.readLine();
        System.out.println("Message received from the client : " + message);
    }
}
