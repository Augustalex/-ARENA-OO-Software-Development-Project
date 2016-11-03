/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClient;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author S132063
 */
public class TestClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       TestClient ap = new TestClient();
       ap.run();
        
    }
    private void run() throws IOException{
        Socket clientSocket = new Socket("localhost", 4444);
        recieveMessage(clientSocket);
        sendMessage(clientSocket, "hello \n");
        recieveMessage(clientSocket);
        sendMessage(clientSocket, "hello \n");
        recieveMessage(clientSocket);
        sendMessage(clientSocket, "hello \n");
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
        String message;
        InputStream is = clientSocket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        message = br.readLine();
        System.out.println("Message received from the server : " + message);
    }
}
