/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testServer;

/**
 *
 * @author S132063
 */
public class JavaApplication9 {
    
    public static void main(String[] args) {
        ThreadedServer server = new ThreadedServer();
        new Thread(server).start();

    /*
         try {
        Thread.sleep(20 * 1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    */          
    //System.out.println("Stopping Server");
    //server.stop();
    }
    
}
